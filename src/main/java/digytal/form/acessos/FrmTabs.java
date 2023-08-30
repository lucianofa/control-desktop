package digytal.form.acessos;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmTabs extends JPanel {
	private JTabbedPane tabs = new JTabbedPane();
	
	public FrmTabs() {
		setLayout(new BorderLayout());
		add(tabs, BorderLayout.CENTER);
		//FrmConsultaMarca m = new FrmConsultaMarca();
		//tabs.add(m,"Marcas");
	}
}
