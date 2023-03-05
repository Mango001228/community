package cn.mango.community.exception;

//使用接口，是为了防止所有异常枚举类都集中到一个枚举类，后续维护不方便
public interface ICustomizeErrorCode {
    String getMessage();
}
