1. domain层和infra层都是不包含任何业务逻辑
2. 将所有复杂的东西（包含业务逻辑）和自身的一些入口写在application层中
3. 比如我们常说的controller，job，mq（消费者）等。
