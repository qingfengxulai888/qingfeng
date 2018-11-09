package com.chat.app.util.aop;


import com.us.base.util.aop.BaseAop;


/**
 * Created by righteyte on 16/6/17.
 */
//参考网址:http://blog.csdn.net/jiaobuchong/article/details/50420379
/*@Aspect
@Configuration*/
public class Aop extends BaseAop {

/*
    private Logger logger = Logger.getLogger(Aop.class);
    // 定义切点Pointcut
    //切入点表达式,现在是所有controller里面的所有方法都拦截住..
    @Pointcut("execution(* UserInfoService.*(..))")
    public void excuteService() {
    }

    *//**
     * 环绕方法,获取返回值只能写到around方法里面
     * @param pjp
     * @return
     * @throws Throwable
     *//*
    @Around("excuteService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // result的值就是被拦截方法的返回值,现在是所有controller都会被拦截.
        Object result = null;

        //获取请求的方法
        String method = this.getMethod(pjp);
        //获取输入参数
        Object[] parameters = this.getParameters(pjp);



        try {   //导致service无法抛出异常......
            //在方法执行之前,打印日志
            logger.info(" 调用的方法是:"+method);

            for (Object arg : parameters) {

                logger.info("传入参数arg是:"+arg.toString());
            }
            //执行方法,result就是controller的返回值
            result = pjp.proceed();
            //方法执行之后,打印返回值
            logger.info("service返回的参数是:"+result);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
       //将执行结果返回
        return result;
    }*/

 /*   *//**
     * 前置方法
     * @return
     * @throws Exception
     *//*
    @Before("execution(* com.us.profile.app.app.UserController.*(..))")
    public Object doBefore() throws Exception{
        System.out.println("this is before");
        return null;
    }

    *//**
     * 后置方法
     * @return
     * @throws Exception
     *//*
    @After("execution(* com.us.product.app.app.UserController.*(..))")
    public Object doAfter() throws Exception{

        System.out.println("this is after");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        //获取访问路径中的url,请求的方法,uri,queryString(?a=1&b=2)
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println("url:"+url);
        System.out.print(",method:"+method+",uri:"+uri+",queryString:"+queryString);

        return null;
    }*/
}
