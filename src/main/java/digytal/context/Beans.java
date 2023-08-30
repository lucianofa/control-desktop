package digytal.context;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import digytal.utils.model.Sessao;

@Configuration
public class Beans {
	@Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Sessao sessao() {
        return new Sessao();
    }
    @Bean
	public RestTemplate restTemplate() {
    	HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	  requestFactory.setReadTimeout(600000);
	  requestFactory.setConnectTimeout(600000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
}
