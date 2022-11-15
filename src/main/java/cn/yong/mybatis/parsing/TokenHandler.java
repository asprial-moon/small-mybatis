package cn.yong.mybatis.parsing;

/**
 * @author Allen
 * @desc 记号处理器
 * @date 2022/11/15
 */
public interface TokenHandler {

    String handleToken(String content);

}
