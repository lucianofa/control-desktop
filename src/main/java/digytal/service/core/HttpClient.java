package digytal.service.core;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JacksonException;
import digytal.utils.model.Sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import digytal.utils.business.BusinessException;
import digytal.utils.http.HttpResponse;
import digytal.utils.http.Mapper;
import digytal.utils.http.Response;
import digytal.utils.sql.Filter;

//https://www.baeldung.com/how-to-use-resttemplate-with-basic-authentication-in-spring
public abstract class HttpClient {

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	private Sessao sessao;

	@Value("${app.client.root.url:http://localhost:8080/}")
	private String rootUrl;

	private String resource = "";

	public HttpClient(String sufix) {
		this.resource = sufix;
	}
	
	protected String paths(Object... sufixs) {
		String path =  Arrays.stream(sufixs).map(s -> s.toString()).collect(Collectors.joining("/"));
		return path;
	}
	private String getEndpoint(Object... sufixs) {
		String endpoint = rootUrl.trim() + resource.trim();
		String sufix =  Arrays.stream(sufixs).map(s -> s.toString()).collect(Collectors.joining("/"));
		endpoint = endpoint + (sufix.trim().length() > 0 ? "/" + sufix : "");
		return endpoint;
	}
	
	public HttpResponse patch(Object object, Object... sufixs) throws BusinessException {
		return exchange(HttpMethod.PATCH, object, sufixs);
	}

	public HttpResponse post(Object object, Object... sufixs) throws BusinessException {
		return exchange(HttpMethod.POST, object, sufixs);
	}

	public HttpResponse put(Object object, Object... sufixs) throws BusinessException {
		return exchange(HttpMethod.PUT, object, sufixs);
	}

	public HttpResponse get(Object... sufixs) throws BusinessException {
		return get(null, sufixs);
	}

	public HttpResponse get(Filter filter, Object... sufixs) throws BusinessException {
		return exchange(HttpMethod.GET, filter, sufixs);
	}

	public HttpResponse exchange(HttpMethod method, Object object, Object... sufixs) throws BusinessException {
		try {
			String url = getEndpoint(sufixs);
			String token = Objects.toString(sessao.getToken(),"") ;

			HttpHeaders headers = createBearerHeaders(token);
			HttpEntity<Object> request = new HttpEntity<>(object, headers);

			Map<String, Object> params = new HashMap<>();
			/*
			if (object != null && object instanceof Filter) {
				params = ((Filter) object).filters;
				String parametrizedArgs = params.keySet().stream().map(k -> String.format("%s={%s}", k, k))
						.collect(Collectors.joining("&"));
				url = parametrizedArgs.trim().isEmpty() ? url : url.concat("?").concat(parametrizedArgs);
			}
			System.out.println(method.name() + "--->" + url);
			*/
			System.out.println(method + " -> " + url);
			ResponseEntity<Response> responseEntity = restTemplate.exchange(url, method, request, Response.class, params);
			return HttpResponse.of(responseEntity.getBody());
		} catch (HttpClientErrorException httpex) {
			Response response =null;
			try {
				response  = Mapper.instance.readValue(httpex.getResponseBodyAsString(), Response.class);
			} catch (JacksonException e) {
				throw new BusinessException("500","Erro Desconhecido","Contact o Suporte");
			}
			throw new BusinessException(response.getStatus().getCode().toString(), response.getStatus().getMessage(),response.getStatus().getSuggestion());
		}
	}

	private HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	private HttpHeaders createBasicHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	private HttpHeaders createBearerHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer " + token.replaceAll("Bearer", ""));
		return headers;
		
	}
}
