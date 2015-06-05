1、src目录 

├  apis.app --系统 基础知识

├  apis.appwidget --窗口控件开发 相关

├  apis.location --位置相关

├  apis.net --网络相关

├  apis.phone --手机功能

├  apis.project --项目Demo

├  apis.thread --多线程

├  apis.ui --界面相关

	├ resources --资源文件

	├ views --系统控件

	├ widget --自定义View



2、libs目录 
├  android-support-v4.jar --v4兼容包



==
AnimatedVectorDrawable 5.0动画path

============================下面是一些Android权限============================
 
常用权限：


读写存储卡

装载和卸载文件系统
android.permission.WRITE_EXTERNAL_STORAGE
android.permission.READ_EXTERNAL_STORAGE
android.permission.MOUNT_UNMOUNT_FILESYSTEMS
网络连接
android.permission.INTERNET
android.permission.ACCESS_NETWORK_STATE
android.permission.ACCESS_WIFI_STATE
android.permission.CHANGE_WIFI_STATE
开机启动
android.permission.RECEIVE_BOOT_COMPLETED
精准的GPS位置
基于网络的粗略的位置
获取模拟定位信息（调试）
android.permission.ACCESS_FINE_LOCATION
android.permission.ACCESS_COARSE_LOCATION
android.permission.ACCESS_MOCK_LOCATION
短信
android.permission.BROADCAST_SMS
android.permission.READ_SMS
android.permission.SEND_SMS
android.permission.RECEIVE_SMS
android.permission.WRITE_SMS
拨打电话
允许程序拨打电话 （任何电话）
android.permission.CALL_PHONE
android.permission.CALL_PRIVILEGED
读取联系人
读取通话记录
android.permission.READ_CONTACTS
android.permission.WRITE_CONTACTS
android.permission.READ_CALL_LOG
android.permission.WRITE_CALL_LOG
安装/卸载快捷方式
com.android.launcher.permission.INSTALL_SHORTCUT
com.android.launcher.permission.UNINSTALL_SHORTCUT
录音
修改声音设置
android.permission.RECORD_AUDIO
android.permission.MODIFY_AUDIO_SETTINGS
振动
android.permission.VIBRATE
默认
android.permission.BAIDU_LOCATION_SERVICE
读写手机状态和身份
android.permission.READ_PHONE_STATE
装载和卸载文件系统
android.permission.MOUNT_UNMOUNT_FILESYSTEMS
查阅敏感日志数据
android.permission.READ_LOGS
发送持久广播
android.permission.BROADCAST_STICKY
修改全局系统设置
android.permission.WRITE_SETTINGS
唤醒
android.permission.WAKE_LOCK
关闭程序
android.permission.RESTART_PACKAGES
android.permission.KILL_BACKGROUND_PROCESSES
android浏览器插件开发
android.webkit.permission.PLUGIN
禁用键盘锁
android.permission.DISABLE_KEYGUARD
写入浏览器的收藏夹和历史记录
读
com.android.browser.permission.WRITE_HISTORY_BOOKMARKS
com.android.browser.permission.READ_HISTORY_BOOKMARKS
调用Camera
android.permission.CAMERA

android.permission.WRITE_APN_SETTINGS

android.permission.GET_TASKS
 	 

自定义权限：
http://berdy.iteye.com/blog/1782854
http://blog.csdn.net/myhu730/article/details/6816880

权限：http://blog.csdn.net/dianyueneo/article/details/7224756

访问登记属性	android.permission.ACCESS_CHECKIN_PROPERTIES，读取或写入登记check-in数据库属性表的权限
获取错略位置	android.permission.ACCESS_COARSE_LOCATION，通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
获取精确位置	android.permission.ACCESS_FINE_LOCATION，通过GPS芯片接收卫星的定位信息，定位精度达10米以内
访问定位额外命令	android.permission.ACCESS_LOCATION_EXTRA_COMMANDS，允许程序访问额外的定位提供者指令
获取模拟定位信息	android.permission.ACCESS_MOCK_LOCATION，获取模拟定位信息，一般用于帮助开发者调试应用
获取网络状态	android.permission.ACCESS_NETWORK_STATE，获取网络信息状态，如当前的网络连接是否有效
访问Surface Flinger	android.permission.ACCESS_SURFACE_FLINGER，Android平台上底层的图形显示支持，一般用于游戏或照相机预览界面和底层模式的屏幕截图
获取WiFi状态	android.permission.ACCESS_WIFI_STATE，获取当前WiFi接入的状态以及WLAN热点的信息
账户管理	android.permission.ACCOUNT_MANAGER，获取账户验证信息，主要为GMail账户信息，只有系统级进程才能访问的权限
验证账户	android.permission.AUTHENTICATE_ACCOUNTS，允许一个程序通过账户验证方式访问账户管理ACCOUNT_MANAGER相关信息
电量统计	android.permission.BATTERY_STATS，获取电池电量统计信息
绑定小插件	android.permission.BIND_APPWIDGET，允许一个程序告诉appWidget服务需要访问小插件的数据库，只有非常少的应用才用到此权限
绑定设备管理	android.permission.BIND_DEVICE_ADMIN，请求系统管理员接收者receiver，只有系统才能使用
绑定输入法	android.permission.BIND_INPUT_METHOD ，请求InputMethodService服务，只有系统才能使用
绑定RemoteView	android.permission.BIND_REMOTEVIEWS，必须通过RemoteViewsService服务来请求，只有系统才能用
绑定壁纸	android.permission.BIND_WALLPAPER，必须通过WallpaperService服务来请求，只有系统才能用
使用蓝牙	android.permission.BLUETOOTH，允许程序连接配对过的蓝牙设备
蓝牙管理	android.permission.BLUETOOTH_ADMIN，允许程序进行发现和配对新的蓝牙设备
变成砖头	android.permission.BRICK，能够禁用手机，非常危险，顾名思义就是让手机变成砖头
应用删除时广播	android.permission.BROADCAST_PACKAGE_REMOVED，当一个应用在删除时触发一个广播
收到短信时广播	android.permission.BROADCAST_SMS，当收到短信时触发一个广播
连续广播	android.permission.BROADCAST_STICKY，允许一个程序收到广播后快速收到下一个广播
WAP PUSH广播	android.permission.BROADCAST_WAP_PUSH，WAP PUSH服务收到后触发一个广播
拨打电话	android.permission.CALL_PHONE，允许程序从非系统拨号器里输入电话号码
通话权限	android.permission.CALL_PRIVILEGED，允许程序拨打电话，替换系统的拨号器界面
拍照权限	android.permission.CAMERA，允许访问摄像头进行拍照
改变组件状态	android.permission.CHANGE_COMPONENT_ENABLED_STATE，改变组件是否启用状态
改变配置	android.permission.CHANGE_CONFIGURATION，允许当前应用改变配置，如定位
改变网络状态	android.permission.CHANGE_NETWORK_STATE，改变网络状态如是否能联网
改变WiFi多播状态	android.permission.CHANGE_WIFI_MULTICAST_STATE，改变WiFi多播状态
改变WiFi状态	android.permission.CHANGE_WIFI_STATE，改变WiFi状态
清除应用缓存	android.permission.CLEAR_APP_CACHE，清除应用缓存
清除用户数据	android.permission.CLEAR_APP_USER_DATA，清除应用的用户数据
底层访问权限	android.permission.CWJ_GROUP，允许CWJ账户组访问底层信息
手机优化大师扩展权限	android.permission.CELL_PHONE_MASTER_EX，手机优化大师扩展权限
控制定位更新	android.permission.CONTROL_LOCATION_UPDATES，允许获得移动网络定位信息改变
删除缓存文件	android.permission.DELETE_CACHE_FILES，允许应用删除缓存文件
删除应用	android.permission.DELETE_PACKAGES，允许程序删除应用
电源管理	android.permission.DEVICE_POWER，允许访问底层电源管理
应用诊断	android.permission.DIAGNOSTIC，允许程序到RW到诊断资源
禁用键盘锁	android.permission.DISABLE_KEYGUARD，允许程序禁用键盘锁
转存系统信息	android.permission.DUMP，允许程序获取系统dump信息从系统服务
状态栏控制	android.permission.EXPAND_STATUS_BAR，允许程序扩展或收缩状态栏
工厂测试模式	android.permission.FACTORY_TEST，允许程序运行工厂测试模式
使用闪光灯	android.permission.FLASHLIGHT，允许访问闪光灯
强制后退	android.permission.FORCE_BACK，允许程序强制使用back后退按键，无论Activity是否在顶层
访问账户Gmail列表	android.permission.GET_ACCOUNTS，访问GMail账户列表
获取应用大小	android.permission.GET_PACKAGE_SIZE，获取应用的文件大小
获取任务信息	android.permission.GET_TASKS，允许程序获取当前或最近运行的应用
允许全局搜索	android.permission.GLOBAL_SEARCH，允许程序使用全局搜索功能
硬件测试	android.permission.HARDWARE_TEST，访问硬件辅助设备，用于硬件测试
注射事件	android.permission.INJECT_EVENTS，允许访问本程序的底层事件，获取按键、轨迹球的事件流
安装定位提供	android.permission.INSTALL_LOCATION_PROVIDER，安装定位提供
安装应用程序	android.permission.INSTALL_PACKAGES，允许程序安装应用
内部系统窗口	android.permission.INTERNAL_SYSTEM_WINDOW，允许程序打开内部窗口，不对第三方应用程序开放此权限
访问网络	android.permission.INTERNET，访问网络连接，可能产生GPRS流量
结束后台进程	android.permission.KILL_BACKGROUND_PROCESSES，允许程序调用killBackgroundProcesses(String).方法结束后台进程
管理账户	android.permission.MANAGE_ACCOUNTS，允许程序管理AccountManager中的账户列表
管理程序引用	android.permission.MANAGE_APP_TOKENS，管理创建、摧毁、Z轴顺序，仅用于系统
高级权限	android.permission.MTWEAK_USER，允许mTweak用户访问高级系统权限
社区权限	android.permission.MTWEAK_FORUM，允许使用mTweak社区权限
软格式化	android.permission.MASTER_CLEAR，允许程序执行软格式化，删除系统配置信息
修改声音设置	android.permission.MODIFY_AUDIO_SETTINGS，修改声音设置信息
修改电话状态	android.permission.MODIFY_PHONE_STATE，修改电话状态，如飞行模式，但不包含替换系统拨号器界面
格式化文件系统	android.permission.MOUNT_FORMAT_FILESYSTEMS，格式化可移动文件系统，比如格式化清空SD卡
挂载文件系统	android.permission.MOUNT_UNMOUNT_FILESYSTEMS，挂载、反挂载外部文件系统
允许NFC通讯	android.permission.NFC，允许程序执行NFC近距离通讯操作，用于移动支持
永久Activity	android.permission.PERSISTENT_ACTIVITY，创建一个永久的Activity，该功能标记为将来将被移除
处理拨出电话	android.permission.PROCESS_OUTGOING_CALLS，允许程序监视，修改或放弃播出电话
读取日程提醒	android.permission.READ_CALENDAR，允许程序读取用户的日程信息
读取联系人	android.permission.READ_CONTACTS，允许应用访问联系人通讯录信息
屏幕截图	android.permission.READ_FRAME_BUFFER，读取帧缓存用于屏幕截图
读取收藏夹和历史记录	com.android.browser.permission.READ_HISTORY_BOOKMARKS，读取浏览器收藏夹和历史记录
读取输入状态	android.permission.READ_INPUT_STATE，读取当前键的输入状态，仅用于系统
读取系统日志	android.permission.READ_LOGS，读取系统底层日志
读取电话状态	android.permission.READ_PHONE_STATE，访问电话状态
读取短信内容	android.permission.READ_SMS，读取短信内容
读取同步设置	android.permission.READ_SYNC_SETTINGS，读取同步设置，读取Google在线同步设置
读取同步状态	android.permission.READ_SYNC_STATS，读取同步状态，获得Google在线同步状态
重启设备	android.permission.REBOOT，允许程序重新启动设备
开机自动允许	android.permission.RECEIVE_BOOT_COMPLETED，允许程序开机自动运行
接收彩信	android.permission.RECEIVE_MMS，接收彩信
接收短信	android.permission.RECEIVE_SMS，接收短信
接收Wap Push	android.permission.RECEIVE_WAP_PUSH，接收WAP PUSH信息
录音	android.permission.RECORD_AUDIO，录制声音通过手机或耳机的麦克
排序系统任务	android.permission.REORDER_TASKS，重新排序系统Z轴运行中的任务
结束系统任务	android.permission.RESTART_PACKAGES，结束任务通过restartPackage(String)方法，该方式将在外来放弃
发送短信	android.permission.SEND_SMS，发送短信
设置Activity观察其	android.permission.SET_ACTIVITY_WATCHER，设置Activity观察器一般用于monkey测试
设置闹铃提醒	com.android.alarm.permission.SET_ALARM，设置闹铃提醒
设置总是退出	android.permission.SET_ALWAYS_FINISH，设置程序在后台是否总是退出
设置动画缩放	android.permission.SET_ANIMATION_SCALE，设置全局动画缩放
设置调试程序	android.permission.SET_DEBUG_APP，设置调试程序，一般用于开发
设置屏幕方向	android.permission.SET_ORIENTATION，设置屏幕方向为横屏或标准方式显示，不用于普通应用
设置应用参数	android.permission.SET_PREFERRED_APPLICATIONS，设置应用的参数，已不再工作具体查看addPackageToPreferred(String) 介绍
设置进程限制	android.permission.SET_PROCESS_LIMIT，允许程序设置最大的进程数量的限制
设置系统时间	android.permission.SET_TIME，设置系统时间
设置系统时区	android.permission.SET_TIME_ZONE，设置系统时区
设置桌面壁纸	android.permission.SET_WALLPAPER，设置桌面壁纸
设置壁纸建议	android.permission.SET_WALLPAPER_HINTS，设置壁纸建议
发送永久进程信号	android.permission.SIGNAL_PERSISTENT_PROCESSES，发送一个永久的进程信号
状态栏控制	android.permission.STATUS_BAR，允许程序打开、关闭、禁用状态栏
访问订阅内容	android.permission.SUBSCRIBED_FEEDS_READ，访问订阅信息的数据库
写入订阅内容	android.permission.SUBSCRIBED_FEEDS_WRITE，写入或修改订阅内容的数据库
显示系统窗口	android.permission.SYSTEM_ALERT_WINDOW，显示系统窗口
更新设备状态	android.permission.UPDATE_DEVICE_STATS，更新设备状态
使用证书	android.permission.USE_CREDENTIALS，允许程序请求验证从AccountManager
使用SIP视频	android.permission.USE_SIP，允许程序使用SIP视频服务
使用振动	android.permission.VIBRATE，允许振动
唤醒锁定	android.permission.WAKE_LOCK，允许程序在手机屏幕关闭后后台进程仍然运行
写入GPRS接入点设置	android.permission.WRITE_APN_SETTINGS，写入网络GPRS接入点设置
写入日程提醒	android.permission.WRITE_CALENDAR，写入日程，但不可读取
写入联系人	android.permission.WRITE_CONTACTS，写入联系人，但不可读取
写入外部存储	android.permission.WRITE_EXTERNAL_STORAGE，允许程序写入外部存储，如SD卡上写文件
写入Google地图数据	android.permission.WRITE_GSERVICES，允许程序写入Google Map服务数据
写入收藏夹和历史记录	com.android.browser.permission.WRITE_HISTORY_BOOKMARKS，写入浏览器历史记录或收藏夹，但不可读取
读写系统敏感设置	android.permission.WRITE_SECURE_SETTINGS，允许程序读写系统安全敏感的设置项
读写系统设置	android.permission.WRITE_SETTINGS，允许读写系统设置项
编写短信	android.permission.WRITE_SMS，允许编写短信
写入在线同步设置	android.permission.WRITE_SYNC_SETTINGS，写入Google在线同步设置
         

============================下面是一些java异常集============================


算术异常类：ArithmeticExecption

空指针异常类：NullPointerException

类型强制转换异常：ClassCastException

数组负下标异常：NegativeArrayException

数组下标越界异常：ArrayIndexOutOfBoundsException

违背安全原则异常：SecturityException

文件已结束异常：EOFException

文件未找到异常：FileNotFoundException

字符串转换为数字异常：NumberFormatException
操作数据库异常：SQLException
输入输出异常：IOException
方法未找到异常：NoSuchMethodException

java.lang.AbstractMethodError

抽象方法错误。当应用试图调用抽象方法时抛出。

java.lang.AssertionError

断言错。用来指示一个断言失败的情况。

java.lang.ClassCircularityError

类循环依赖错误。在初始化一个类时，若检测到类之间循环依赖则抛出该异常。

java.lang.ClassFormatError

类格式错误。当Java虚拟机试图从一个文件中读取Java类，而检测到该文件的内容不符合类的有效格式时抛出。

java.lang.Error

错误。是所有错误的基类，用于标识严重的程序运行问题。这些问题通常描述一些不应被应用程序捕获的反常情况。

java.lang.ExceptionInInitializerError

初始化程序错误。当执行一个类的静态初始化程序的过程中，发生了异常时抛出。静态初始化程序是指直接包含于类中的static语句段。

java.lang.IllegalAccessError

违法访问错误。当一个应用试图访问、修改某个类的域（Field）或者调用其方法，但是又违反域或方法的可见性声明，则抛出该异常。

java.lang.IncompatibleClassChangeError

不兼容的类变化错误。当正在执行的方法所依赖的类定义发生了不兼容的改变时，抛出该异常。一般在修改了应用中的某些类的声明定义而没有对整个应用重新编译而直接运行的情况下，容易引发该错误。

java.lang.InstantiationError

实例化错误。当一个应用试图通过Java的new操作符构造一个抽象类或者接口时抛出该异常.

java.lang.InternalError

内部错误。用于指示Java虚拟机发生了内部错误。

java.lang.LinkageError

链接错误。该错误及其所有子类指示某个类依赖于另外一些类，在该类编译之后，被依赖的类改变了其类定义而没有重新编译所有的类，进而引发错误的情况。

java.lang.NoClassDefFoundError

未找到类定义错误。当Java虚拟机或者类装载器试图实例化某个类，而找不到该类的定义时抛出该错误。

java.lang.NoSuchFieldError

域不存在错误。当应用试图访问或者修改某类的某个域，而该类的定义中没有该域的定义时抛出该错误。

java.lang.NoSuchMethodError

方法不存在错误。当应用试图调用某类的某个方法，而该类的定义中没有该方法的定义时抛出该错误。

java.lang.OutOfMemoryError

内存不足错误。当可用内存不足以让Java虚拟机分配给一个对象时抛出该错误。

java.lang.StackOverflowError

堆栈溢出错误。当一个应用递归调用的层次太深而导致堆栈溢出时抛出该错误。

java.lang.ThreadDeath

线程结束。当调用Thread类的stop方法时抛出该错误，用于指示线程结束。

java.lang.UnknownError

未知错误。用于指示Java虚拟机发生了未知严重错误的情况。

java.lang.UnsatisfiedLinkError

未满足的链接错误。当Java虚拟机未找到某个类的声明为native方法的本机语言定义时抛出。

java.lang.UnsupportedClassVersionError

不支持的类版本错误。当Java虚拟机试图从读取某个类文件，但是发现该文件的主、次版本号不被当前Java虚拟机支持的时候，抛出该错误。

java.lang.VerifyError

验证错误。当验证器检测到某个类文件中存在内部不兼容或者安全问题时抛出该错误。

java.lang.VirtualMachineError

虚拟机错误。用于指示虚拟机被破坏或者继续执行操作所需的资源不足的情况。
java.lang.ArithmeticException

算术条件异常。譬如：整数除零等。

java.lang.ArrayIndexOutOfBoundsException

数组索引越界异常。当对数组的索引值为负数或大于等于数组大小时抛出。

java.lang.ArrayStoreException

数组存储异常。当向数组中存放非数组声明类型对象时抛出。

java.lang.ClassCastException

类造型异常。假设有类A和B（A不是B的父类或子类），O是A的实例，那么当强制将O构造为类B的实例时抛出该异常。该异常经常被称为强制类型转换异常。

java.lang.ClassNotFoundException

找不到类异常。当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常。

java.lang.CloneNotSupportedException

不支持克隆异常。当没有实现Cloneable接口或者不支持克隆方法时,调用其clone()方法则抛出该异常。

java.lang.EnumConstantNotPresentException

枚举常量不存在异常。当应用试图通过名称和枚举类型访问一个枚举对象，但该枚举对象并不包含常量时，抛出该异常。

java.lang.Exception

根异常。用以描述应用程序希望捕获的情况。

java.lang.IllegalAccessException

违法的访问异常。当应用试图通过反射方式创建某个类的实例、访问该类属性、调用该类方法，而当时又无法访问类的、属性的、方法的或构造方法的定义时抛出该异常。

java.lang.IllegalMonitorStateException

违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。

java.lang.IllegalStateException

违法的状态异常。当在Java环境和应用尚未处于某个方法的合法调用状态，而调用了该方法时，抛出该异常。

java.lang.IllegalThreadStateException

违法的线程状态异常。当县城尚未处于某个方法的合法调用状态，而调用了该方法时，抛出异常。

java.lang.IndexOutOfBoundsException

索引越界异常。当访问某个序列的索引值小于0或大于等于序列大小时，抛出该异常。

java.lang.InstantiationException

实例化异常。当试图通过newInstance()方法创建某个类的实例，而该类是一个抽象类或接口时，抛出该异常。

java.lang.InterruptedException

被中止异常。当某个线程处于长时间的等待、休眠或其他暂停状态，而此时其他的线程通过Thread的interrupt方法终止该线程时抛出该异常。

java.lang.NegativeArraySizeException

数组大小为负值异常。当使用负数大小值创建数组时抛出该异常。

java.lang.NoSuchFieldException

属性不存在异常。当访问某个类的不存在的属性时抛出该异常。

java.lang.NoSuchMethodException

方法不存在异常。当访问某个类的不存在的方法时抛出该异常。

java.lang.NullPointerException

空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等。

java.lang.NumberFormatException

数字格式异常。当试图将一个String转换为指定的数字类型，而该字符串确不满足数字类型要求的格式时，抛出该异常。

java.lang.RuntimeException

运行时异常。是所有Java虚拟机正常操作期间可以被抛出的异常的父类。

java.lang.SecurityException

安全异常。由安全管理器抛出，用于指示违反安全情况的异常。

java.lang.StringIndexOutOfBoundsException

字符串索引越界异常。当使用索引值访问某个字符串中的字符，而该索引值小于0或大于等于序列大小时，抛出该异常。

java.lang.TypeNotPresentException

类型不存在异常。当应用试图以某个类型名称的字符串表达方式访问该类型，但是根据给定的名称又找不到该类型是抛出该异常。该异常与ClassNotFoundException的区别在于该异常是unchecked（不被检查）异常，而ClassNotFoundException是checked（被检查）异常。

java.lang.UnsupportedOperationException

不支持的方法异常。指明请求的方法不被支持情况的异常。

异常
javax.servlet.jsp.JspException: Cannot retrieve mapping for action /Login （/Login是你的action名字）

可能原因
action没有再struts-config.xml 中定义，或没有找到匹配的action，例如在JSP文件中使用 <html:form action=”Login.do”.将表单提交给Login.do处理，如果出现上述异常，请查看struts-config.xml中的定义部分，有时可能是打错了字符或者是某些不符合规则，可以使用strutsconsole工具来检查。
—————————————————————————————————————–
异常
org.apache.jasper.JasperException: Cannot retrieve definition for form bean null

可能原因

这个异常是因为Struts根据struts-config.xml中的mapping没有找到action期望的form bean。大部分的情况可能是因为在form-bean中设置的name属性和action中设置的name属性不匹配所致。换句话说，action和form都应该各自有一个name属性，并且要精确匹配，包括大小写。这个错误当没有name属性和action关联时也会发生，如果没有在action中指定name属性，那么就没有name属性和action相关联。当然当action制作某些控制时，譬如根据参数值跳转到相应的jsp页面，而不是处理表单数据，这是就不用name属性，这也是action的使用方法之一。
—————————————————————————————————————–
异常
No action instance for path /xxxx could be created

可能原因
特别提示：因为有很多中情况会导致这个错误的发生，所以推荐大家调高你的web服务器的日志/调试级别，这样可以从更多的信息中看到潜在的、在试图创建action类时发生的错误，这个action类你已经在struts-config.xml中设置了关联（即添加了<action>标签）。

在struts-config.xml中通过action标签的class属性指定的action类不能被找到有很多种原因，例如：定位编译后的.class文件失败。Failure to place compiled .class file for the action in the classpath (在web开发中，class的的位置在r WEB-INF/classes，所以你的action class必须要在这个目录下。例如你的action类位于WEB-INF/classes/action/Login.class,那么在struts-config.xml中设置action的属性type时就是action.Login).
拼写错误，这个也时有发生，并且不易找到，特别注意第一个字母的大小写和包的名称。
—————————————————————————————————————–
异常
javax.servlet.jsp.JspException: No getter method for property username of bean org.apache.struts.taglib.html.BEAN

可能原因
没有位form bean中的某个变量定义getter 方法

这个错误主要发生在表单提交的FormBean中，用struts标记<html:text property=”username”>时，在FormBean中必须有一个getUsername()方法。注意字母“U”。
—————————————————————————————————————–
异常
java.lang.NoClassDefFoundError: org/apache/struts/action/ActionForm

可能原因
这个错误主要发生在在classpath中找不到相应的Java .class文件。如果这个错误发生在web应用程序的运行时，主要是因为指定的class文件不在web server的classpath中（/WEB-INF/classes 和 /WEB-INF/lib）。在上面的错误中，原因是找不到ActionForm类。
—————————————————————————————————————–
异常
javax.servlet.jsp.JspException: Exception creating bean of class org.apache.struts.action.ActionForm: {1}

可能原因
Instantiating Struts-provided ActionForm class directly instead of instantiating a class derived off ActionForm. This mightoccur implicitly if you specify that a form-bean is this Struts ActionForm class rather than specifying a child of this classfor the form-bean.

Not associating an ActionForm-descended class with an action can also lead to this error.
—————————————————————————————————————–
异常
javax.servlet.jsp.JspException: Cannot find ActionMappings or ActionFormBeans collection

可能原因
不是标识Struts actionServlet的<servlet>标记就是映射.do扩展名的<sevlet-mapping>标记或者两者都没有在web.xml中声明。

在struts-config.xml中的打字或者拼写错误也可导致这个异常的发生。例如缺少一个标记的关闭符号/>。最好使用struts console工具检查一下。

另外，load-on-startup必须在web.xml中声明，这要么是一个空标记，要么指定一个数值，这个数值用来表servlet运行的优先级，数值越大优先级越低。

还有一个和使用load-on-startup有关的是使用Struts预编译JSP文件时也可能导致这个异常。
—————————————————————————————————————–
异常
java.lang.NullPointerException at org.apache.struts.util.RequestUtils.forwardURL(RequestUtils.java:1223)

可能原因
在struts-config.xml中的forward元素缺少path属性。例如应该是如下形式：
<forward name=”userhome” path=”/user/userhome.jsp”/>
—————————————————————————————————————–
异常
javax.servlet.jsp.JspException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope
Probable Causes
试图在Struts的form标记外使用form的子元素。这常常发生在你在</html:form>后面使用Struts的html标记。另外要注意可能你不经意使用的无主体的标记，如<html:form … />，这样web 服务器解析时就当作一个无主体的标记，随后使用的所有<html>标记都被认为是在这个标记之外的，如又使用了<html:text property=”id”>还有就是在使用taglib引入HTML标记库时，你使用的prefix的值不是html。
—————————————————————————————————————–
异常
javax.servlet.jsp.JspException: Missing message for key xx.xx.xx

Probable Causes
这个key的值对没有在资源文件ApplicationResources.properties中定义。如果你使用eclipse时经常碰到这样的情况，当项目重新编译时，eclipse会自动将classes目录下的资源文件删除。

资源文件ApplicationResources.properties 不在classpath中应将资源文件放到 WEB-INF/classes 目录下，当然要在struts-config.xml中定义)
—————————————————————————————————————–
异常
Cannot find message resources under key org.apache.struts.action.MESSAGE

可能原因
很显然，这个错误是发生在使用资源文件时，而Struts没有找到资源文件。

Implicitly trying to use message resources that are not available (such as using empty html:options tag instead of specifyingthe options in its body — this assumes options are specified in ApplicationResources.properties file)

XML parser issues — too many, too few, incorrect/incompatible versions
—————————————————————————————————————–
异常
Strange and seemingly random characters in HTML and on screen, but not in original JSP or servlet.

可能原因
混和使用Struts的html:form标记和标准的HTML标记不正确。

使用的编码样式在本页中不支持。
—————————————————————————————————————–
异常
“Document contained no data” in Netscape

No data rendered (completely empty) page in Microsoft Internet Explorer

可能原因
使用一个Action的派生类而没有实现perform()方法或execute()方法。在Struts1.0中实现的是perform()方法，在Struts1.1中实现的是execute()方法，但Struts1.1向后兼容perform()方法。但你使用Struts1.1创建一个Action的派生类，并且实现了execute()方法，而你在Struts1.0中运行的话，就会得到”Document contained nodata” error message in Netscape or a completely empty (no HTML whatsoever) page rendered in Microsoft Internet Explorer.”的错误信息。

—————————————————————————————————————————
异常
ServletException: BeanUtils.populate
解决方案
在用Struts上传文件时,遇到了javax.servlet.ServletException: BeanUtils.populate异常。
我的ActionServlet并没有用到BeanUtils这些工具类。后来仔细检查代码发现是在jsp文件里的form忘了加enctype=&quot;multipart/form-data&quot; 了。所以写程序遇到错误或异常应该从多方面考虑问题存在的可能性，想到系统提示信息以外的东西。
—————————————————————————————————————————-
1. 定义Action后, 如果指定了name, 那么必须要定义一个与它同名的FormBean才能进行form映射.2. 如果定义Action后, 提交页面时出现 “No input attribute for mapping path…” 错误, 则需要在其input属性中定义转向的页面.3. 如果插入新的数据时出现 “Batch update row count wrong:…” 错误, 则说明XXX.hbm.xml中指定的key的类型为原始类型(int, long),因为这种类型会自动分配值, 而这个值往往会让系统认为已经存在该记录, 正确的方法是使用java.lang.Integer或java.lang.Long对象.4. 如果插入数据时出现 “argument type mismatch” 错误, 可能是你使用了Date等特殊对象, 因为struts不能自动从String型转换成Date型,所以, 你需要在Action中手动把String型转换成Date型.5. Hibernate中, Query的iterator()比list()方法快很多.6. 如果出现 “equal symbol expected” 错误, 说明你的strtus标签中包含另一个标签或者变量, 例如:
<html:select property=”test” onchange=”<%=test%>”/>
或者
<html:hidden property=”test” value=”<bean:write name=”t” property=”p”/>”/>
这样的情况…
—————————————————————————————————————————
错误：Exception in thread “main” org.hibernate.exception.SQLGrammarException: Could not execute JDBC batch update原因与解决：      因为Hibernate Tools（或者Eclipse本身的Database Explorer）生成*.hbn.xml工具中包含有catalog=”***”（*表示数据库名称）这样的属性,将该属性删除就可以了
—————————————————————————————————————————
错误：org.hibernate.ObjectDeletedException: deleted object would be re-saved by cascade (remove deleted object from associations)
原因与解决：
方法1 删除Set方的cascade
方法2 解决关联关系后，再删除
方法3 在many-to-one方增加cascade 但值不能是none
最后一招：
检查一下hashCode equals是否使用了id作为唯一标示的选项了；我用uuid.hex时是没有问题的；但是用了native，就不行了，怎么办？删除啊！
—————————————————————————————————————————-
问题：今天用Tomcat 5.5.12，发现原来很好用的系统不能用了，反复测试发现页面中不能包含 taglib，否则会出现以下提示：HTTP Status 500 -type Exception reportMessage description The server encountered an internal error () that prevented it from fulfilling this request.exceptionorg.apache.jasper.JasperException: /index.jsp(1,1) Unable to read TLD “META-INF/tlds/struts-bean.tld” from JAR file”file:*****/WEB-INF/lib/struts.jar”:原因：更新了工程用的lib文件夹下的jar，发布时也发布了servlet.jar和jsp-api.jar。解决：把jsp-api.jar删除就解决这个问题了。—————————————————————————————————————————–
错误： java.lang.NullPointerException
原因： 发现 dao 实例、 manage 实例等需要注入的东西没有被注入（俗称空指针异常）解决：这个时候，你应该查看日志文件；默认是应用服务器的 log 文件，比如 Tomcat 就是 [Tomcat 安装目录 ]/logs ；你会发现提示你：可能是：org.springframework.beans.factory.BeanCreationException: Error creating bean with name ‘sf’ defined in ServletContextresource [/WEB-INF/applicationContext.xml]: Initialization of bean failed; nested exception isorg.hibernate.HibernateException: could not configure from URL: file:src/hibernate.cfg.xmlorg.hibernate.HibernateException: could not configure from URL: file:src/hibernate.cfg.xml……………………….Caused by: java.io.FileNotFoundException: src\hibernate.cfg.xml可能是：org.springframework.beans.factory.BeanCreationException: Error creating bean with name ‘sessionFactory’ defined inServletContext resource [/WEB-INF/applicationContext.xml]: Initialization of bean failed; nested exception isorg.hibernate.MappingException: Resource: com/mcc/coupon/model/UserRole.hbm.xml not foundorg.hibernate.MappingException: Resource: com/mcc/coupon/model/UserRole.hbm.xml not found然后你就知道原因是因为配置文件的解析出了错误，这个通过 Web 页面是看不出来的。更多的是持久化影射文件出的错误；导致了没有被解析；当然你需要的功能就无法使用了。
—————————————————————————————————————————-
错误：StandardWrapperValve[action]: Servlet.service() for servlet action threw exception
javax.servlet.jsp.JspException: Cannot retrieve mapping for action /settlementTypeManage
或者：      type Status report      message Servlet action is not available      description The requested resource (Servlet action is not available) is not available.
原因： 同 上
—————————————————————————————————————————-
错误StandardWrapperValve[jsp]: Servlet.service() for servlet jsp threw exceptionjava.lang.ClassNotFoundException: org.apache.struts.taglib.bean.CookieTei界面错误具体描述：
org.apache.jasper.JasperException: Failed to load or instantiate TagExtraInfo class: org.apache.struts.taglib.bean.CookieTei