.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static rout(II)I
.limit stack 100
.limit locals 100
	iload_1
	iload_1
	imul
	ldc 1
	iadd
	istore_0
	iload_0
	ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 500
	istore_0
	iload_0
	ldc 5
	invokestatic SumProgram/rout(II)I
	return
.end method
