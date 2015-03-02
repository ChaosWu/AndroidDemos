package cn.android.demo;

public interface TermList {

	String application = "应用：一个显示一个图形用户界面给用户的特定样式程序";

	String condition = "条件：一个用来同步资源访问的结构，线程等待某一条件来决定是否被允许继续运行，直到其他线程显示的给该条件发送信号";

	String critical_section = "临界区：同一时间只能不被一个线程执行的代码";

	String input_source = "输入源：一个线程的异步事件源，输入源可以是基于端口或者手动触发，并且必须被附加到某一个线程的run loop上面";

	String join_thread = "可连接的线程：退出时资源不会被立即回收的线程，可连接的线程在资源被回收之前必须被显示脱离或由其他线程连接，可连接线程提供了一个返回值给连接他的线程";

	String main_thread = "主线程：当创建进程时一起创建的特定类型的线程，当程序的主线程退出，则程序退出";

	String mutex = "互斥锁：提供共享资源互斥反问的锁，一个互斥锁同一时间只能被一个线程拥有，试图获取一个已经被其他线程拥有的互斥锁，会把当前线程置于休眠状态直到该锁被其他线程释放并让其当前线程获得";

	String operation_object = "操作对象：NSOperation类的实例，操作对象封装了和某一任务相关的代码和数据到一个执行单元里面";

	String operation_queue = "操作队列：NSOPerationQueue类的实例，操作队列管理操作对象的执行";

	String process = "进程：应用或程序的运行时实例，一个进程拥有独立于分配给其他程序的内存空间和系统资源（包括端口权限），进程总是包含至少一个线程（即主线程）和任意数量的额外线程";

	String program = "程序：可以用来执行某些任务的代码和资源的组合，程序不需要一个图形用户界面，尽管图形应用也被称为程序";

	String recursive_lock = "递归锁：可以被同一线程多次锁住的锁";

	String run_loop = "运行循环：一个事件处理循环，在此间事件被接受并分配给合适的处理线程";

	String run_loop_mode = "Run loop模式：与某一特定名称相关的输入源、定时源和run loop观察者的集合，当运行在某一特定“模式”下，一个run loop 监视和该模式相关的源和观察者";

	String run_loop_object = "Run loop对象：NSRunLoop类或CFRunLoopRef不透明类型的实例，这些对象提供线程里面实现事件处理循环的接口";

	String run_loop_observer = "Run loop观察者：在run loop运行的不同阶段时候接受通知的对象";

	String semaphore = "信号量：一个受保护的变量，它限制共享资源的反问，互斥锁（mutexes）和条件（conditions）都是不同类型的信号量";

	String task = "任务：要执行的工作数量，尽管一些技术（最显著的是Carbon多进程服务-Carbon Multiprocessing Services）使用该术语的意义有时不同，但最通用的用法是表明需要执行的工作数量的抽象概念";

	String thread = "线程：进程里面的一个执行过程流，每个线程都有它自己的空间，但除此之外同一进程的其他线程共享内存";

	String timer_source = "定时源：为线程同步事件的源，定时器产生预定时间将要执行的一次或重复事件";

}