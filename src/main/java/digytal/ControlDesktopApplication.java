package digytal;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.context.Context;
import digytal.form.acessos.FrmLogin;


@SpringBootApplication()
public class ControlDesktopApplication {

	public static void main(String[] args) {
		try {	
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			Context.showSplash("Control Desktop");
			SpringApplication.run(ControlDesktopApplication.class, args);
			Context.closeSplash();
		} catch (Exception e) {
			System.exit(0);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	@Bean
	public CommandLineRunner run(FrmLogin frmLogin) throws Exception {
		return args -> {
			frmLogin.exibir();
		};
	}
}

