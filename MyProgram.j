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
	ldc 1
	ldc 2
	imul
	i2f
	ldc 3
	ldc 4
	imul
	i2f
	fadd
	ldc 5
	ldc 6
	imul
	i2f
	fadd
	ldc 7
	i2f
	ldc 8
	i2f
	fmul
	ldc 5
	i2f
	fdiv
	ldc 10
	i2f
	fmul
	fadd
	fstore_0
	fload_0
	ldc 1
	i2f
	fadd
	fstore_1
	fload_1
	fstore_2
	return
.end method
