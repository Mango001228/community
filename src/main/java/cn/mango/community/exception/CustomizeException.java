package cn.mango.community.exception;

//所有异常抛出都在此处理，避免方法被调用中受到影响
public class CustomizeException extends RuntimeException{
    //定义自己的变量
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    //创建get方法，方便在外层可以拿到
    @Override
    public String getMessage() {
        return message;
    }
}
