package awesome.dashboard;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;


public class Webserver {

    public static void main(String[] args) throws Exception {

        Configurator.currentConfig()
                .level(Level.DEBUG)
                .writer(new ConsoleWriter(), "{date:dd-MM-yyyy HH:mm:ss} {level}\t{class} - {message}")
//                .addWriter(new FileWriter("log.txt"), "{class}.{method}()\t{message}")
                .activate();
        PropertiesService.init();

        Logger.debug("Starter webapp");
        Server server = new Server(8080);
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "awesome.dashboard.api");
        server.start();
        Logger.info("Awesome backend startet: http://localhost:{}", server.getURI().getPort());
        server.join();
    }
}
