.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 0
	istore 0
	ldc 1
	ldc 1
	istore 1
fl_start_0:
	ldc 10
	iload 1
	if_icmplt fl_end_1
	iload 0
	iload 1
	iadd
	istore 0
	iload 1
	ldc 1
	iadd
	istore 1
	goto fl_start_0
fl_end_1:
	iload 1
	ldc 1
	iadd
	istore 2
	return
.end method
