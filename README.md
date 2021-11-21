## SafeGson 一个安全使用Gson库的方式，针对大多数后台数据异常做了容错，防止数据解析异常
SafaGson是一个Gson容错库，原始的Gson针对后台下发空串、object类型下发空字符串、
数组类型下发空串或其他基本类型下发空串时都会直接抛异常。使用SafeGson可以规避这种情况并在解析时返回默认值。
SafeGson参考了GsonFactory的思路，通过registerTypeAdapterFactory在Gson默认的解析adapter之前插入自定义的adapter，gson在解析数据时会按照顺序寻找合适的adapter去反序列化，找到一个合适的就停止查找并会保存adapter到缓存中，下一次遇到相同的解析类型时会直接使用。
因此，adapter中无法保存与解析对象相关的数据，SafeGson修复了GsonFactory的这一bug，并适配了Gson的注解功能
### 使用方法：
1. 获取Gson对象
   Gson gson = SafeGson.getSingletonGson();
2. 使用gson对象解析后台返回json串
    Bean bean = gson.fromJson(jsonStr, Bean.class);

