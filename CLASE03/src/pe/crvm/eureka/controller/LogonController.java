package pe.crvm.eureka.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.crvm.eureka.model.LogonModel;
import pe.egcc.eureka.domain.Empleado;

/**
 * Servlet implementation class LogonController
 */
@WebServlet({ "/LogonSalir", "/LogonIngresar" })
public class LogonController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private LogonModel logonModel;
	
	@Override
	public void init() throws ServletException {
		logonModel = new LogonModel();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

		if (path.equals("/LogonIngresar")) {
			logonIngresar(request, response);
		}
	}


	private void logonIngresar(HttpServletRequest request, HttpServletResponse response) 
								throws ServletException, IOException {
		String destino;
		try {
			// Recogiendo los Datos
			String usuario = request.getParameter("usuario");
			String clave = request.getParameter("clave");
			String nombre = request.getParameter("usuario");
			
			// Proceso
			Empleado bean = logonModel.validar(usuario, clave);
			
			// Validar
			if(bean == null) {// no existe el empleado
				throw new Exception("Datos Incorrectos");
			}
			// Guardar datos del empleado en Sesion
			HttpSession session = request.getSession();
			session.setAttribute("usuario", bean);
			
			
			request.getSession().setAttribute("usuario", usuario); 
			
			destino = "mainPage.jsp";
			
			System.out.println(usuario);
			
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "index.jsp";
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
