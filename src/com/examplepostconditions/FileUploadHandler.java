package com.examplepostconditions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * * @author Sumeet Kumar
 */

public class FileUploadHandler extends HttpServlet {

	private final String UPLOAD_DIRECTORY = "C:/uploads";

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		if (ServletFileUpload.isMultipartContent(request)) {

			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						if (name.endsWith(".xml")) {
							item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
						} else {
							System.out.println("Please upload correct file");
						}
					}
				}

				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);

			}
		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
}