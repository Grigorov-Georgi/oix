package bg.softuni.oix.interceptor;

import bg.softuni.oix.service.StatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("\n\n----------------------------LogInterceptorPerHandle(Start)------------------------------");
        LOGGER.info(request.getRemoteAddr() +
                " accessed resource " + request.getRequestURI() + " @ " + getCurrentTime());
        long startTime = System.currentTimeMillis();
        request.setAttribute("start", startTime);
        LOGGER.info("\n----------------------------LogInterceptorPerHandle(End)------------------------------\n\n");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("\n\n----------------------------LogInterceptorPostHandle(Start)------------------------------");
        LOGGER.info("Request processing ends on " + getCurrentTime());
        LOGGER.info("\n----------------------------LogInterceptorPostHandle(End)------------------------------\n\n");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("\n\n----------------------------LogInterceptorAfterCompletion(Start)------------------------------");
        long endTime = System.currentTimeMillis();
        long startTime = Long.parseLong(request.getAttribute("start") + "");
        LOGGER.info("Total time taken to process request " + (endTime - startTime) + " ms");
        LOGGER.info("\n----------------------------LogInterceptorAfterCompletion(End)------------------------------\n\n");
    }

    private String getCurrentTime(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }
}
