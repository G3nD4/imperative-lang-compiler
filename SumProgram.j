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
	ldc 2
	istore 0
	ldc 5
	i2f
	ldc 2.718
	fmul
	fstore 1
	fload 1
	fstore 2
	fload 2
	ldc 1
	i2f
	fsub
	invokestatic java/lang/Math/round(F)I
	istore 0
	return
.end method
