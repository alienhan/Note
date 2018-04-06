jvm

---
#PermGen
    PermGen space的全称是Permanent Generation space,是指内存的永久保存区域

#加载
1. 编译成class文件(.class 文件结构)
    查看字节码: 
        `javac 编译`
        `javap -verbose Class`
2. 类(Class对象)加载到虚拟机内存过程(
    所谓的类加载就是把.class文件加载到内存成为Class对象)
    loading->linking(verification->preparation->resolution)->initialization->using->unloading

    preparation: 类变量分配内存,设置初始值
    resolution:虚拟机将`常量池内`的符号引用替换成`直接引用`的过程.
    initialization:执行<clinit>()方法的过程
            <clinit>()方法是编译器自动收集类变量,静态语句{}合成的.
    
    类加载器(双亲委派机制,树状结构):
        启动类加载器(bootstrap ClassLoader c++实现)
        扩展类加载器(Extension ClassLoader)
        应用程序类加载器(Application ClassLoader)
        自定义类加载器(User ClassLoader)
        过程:类加载器收到一个类加载请求,先请求委派给父加载器完成加载
        OSGi 非树状结构

    初始化前提:
        1. 遇到 `new getstatic putstatic invokestatic`这4条字节码(java 代码场景 使用new 实例化对象,读取或设置一个类的静态字段(final 除外))
        2. 反射调用时
        3. 初始化类的时候要先初始化父类
        4. main
        5. 动态语言支持

#执行
main->启动线程->调用方法->线程结束

运行时栈帧结构(一个栈帧对应一个方法)
    局部变量表
    操作数栈
    动态链接
    方法返回地址
    附加信息








