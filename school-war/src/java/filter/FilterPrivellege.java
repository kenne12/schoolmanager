/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entities.CompteUtilisateur;
import entities.Privilege;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kenne
 */
@WebFilter(filterName = "FilterPrivellege", urlPatterns = {"/Parametrage/*"})
public class FilterPrivellege implements Filter, Serializable {

    Properties properties;
    private static final boolean debug = true;

    CompteUtilisateur compteUtiliasteur = new CompteUtilisateur();

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterPrivellege() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterPrivellege:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterPrivellege:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;
        HttpSession session = hRequest.getSession();
        System.out.println("====>>>>>> url = " + hRequest.getRequestURI());


        if (session.getAttribute("compte") != null) {

            if ("/login.html".equals(hRequest.getRequestURI())) {
                request.getRequestDispatcher("/index.html").forward(request, response);
            }

            compteUtiliasteur = (CompteUtilisateur) session.getAttribute("compte");
            
            List<String>allAccesses = (List<String>) session.getAttribute("allAccess");

            if (allAccesses.contains(hRequest.getRequestURI())) {
                
                List<Privilege>privileges =  (List<Privilege>) session.getAttribute("privilege");

                if (!privileges.isEmpty()) {
                    boolean drapeau = false;

                    for (Privilege p : privileges) {
                        if (p.getIdmenu().getIdmenu().equals(1)) {
                            drapeau = true;
                            break;
                        }
                    }

                    if (drapeau) {
                        System.err.println("Connecté en tant que admin");
                        chain.doFilter(request, response);
                    } else {
                        List<String>access = (List<String>) session.getAttribute("access");
                        if (access.contains(hRequest.getRequestURI())) {
                            System.err.println("accès réussie");
                            chain.doFilter(request, response);
                        } else {
                            System.err.println("access refusé");
                            chain.doFilter(request, response);
                            request.getRequestDispatcher("/school-war/erreuracces.html").forward(request, response);
                        }
                    }
                }
            } else {
                System.err.println("ressource sans privilege");
                chain.doFilter(request, response);
            }

        } else {
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterPrivellege:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterPrivellege()");
        }
        StringBuilder sb = new StringBuilder("FilterPrivellege(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {

    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    public String getPropertyValue(String key) {
        try {
            // get the value of the property
            if (key == null) {
                System.out.println("=============== key null  ++++++++++++++++++++");
            }
            if (key == "") {
                System.out.println("=============== key empty  ++++++++++++++++++++");
            }
            if (properties == null) {
                System.out.println("=============== properties empty  ++++++++++++++++++++");
            }
            String propValue = properties.getProperty(key);

            System.out.println("key is: " + key);
            System.out.println("Property value is: " + propValue);
            return propValue;
        } catch (Exception ex) {
            return null;
        }
    }
}
