package cn.yong.mybatis.parsing;

/**
 * @author Line
 * @desc 记号处理器
 * @date 2022/9/3
 */
public interface TokenHandler {

    String handleToken(String content);

}
