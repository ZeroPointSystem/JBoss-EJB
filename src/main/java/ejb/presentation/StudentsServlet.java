package ejb.presentation;

import ejb.services.StudentService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentsServlet extends HttpServlet {

    @Inject
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request
            .setAttribute("students", studentService.getAllStudents());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/pages/table.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("studentName"));

//        studentService
//                .persist(Student
//                        .builder()
//                        .name(request
//                                .getHeader("studentName"))
//                        .build());
//
//        request
//            .setAttribute("students", studentService.getAllStudents());
//
//        getServletContext()
//                .getRequestDispatcher("/WEB-INF/pages/table.jsp")
//                .forward(request, response);
    }
}
