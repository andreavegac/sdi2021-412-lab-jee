package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");
		
		if(carrito==null) {
			carrito = new HashMap<String, Integer>();
		}
		
		String producto = request.getParameter("producto");
		if(producto != null) {
			insertarEnCarrito(carrito, producto);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Tienda SDI: carrito</TITLE></HEAD>");
		out.println("<BODY>");
		out.println(carritoEnHTML(carrito, producto) + "<br>");
		out.println("<a href=\"index.jsp\">Volver</a></BODY></HTML>");
		
	}

	private String carritoEnHTML(HashMap<String, Integer> carrito, String producto) {
		String carritoEnHtml="";
		for(String key: carrito.keySet()) {
			carritoEnHtml+="<p>["+key+"], " + carrito.get(key) + " unidades.</p>";
		}
		return carritoEnHtml;
	}

	private void insertarEnCarrito(HashMap<String, Integer> carrito, String producto) {
		int unidades = carrito.get(producto)==null ? 1 : carrito.get(producto).intValue()+1;
		carrito.put(producto, unidades);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}