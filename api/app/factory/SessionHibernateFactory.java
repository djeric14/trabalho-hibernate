package factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionHibernateFactory {
	private static SessionFactory sf;

	public static synchronized Session getHibernateSession() {
		if (sf == null) {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			sf = configuration.buildSessionFactory(serviceRegistry);
		}
		Session s = sf.getCurrentSession();
		return s;
	}
}
