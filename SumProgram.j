.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static multiply(II)I
.limit stack 100
.limit locals 100
	iload 0
	iload 1
	iadd
	ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 5
	ldc 2
	invokestatic SumProgram/multiply(II)I
	istore 0
	return
.end method
