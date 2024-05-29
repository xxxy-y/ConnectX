1. 这里使用的是简单工厂+策略的模式
2. 其中的SubjectTypeHandler是工厂模式中的抽象实体类，BriefTypeHandler等其他的Handler是具体实体类。
3. 其中的Factory就是工厂。
4. 使用自动注入的SubjectTypeHandlerFactory来获取到对应的实体类handler，只需要传入subjectType(int类型)
