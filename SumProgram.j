.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static IsPrime(I)I
.limit stack 100
.limit locals 100
	iload 0
	i2f
	ldc 1
	i2f
	fcmpl
	ifle true_2
	iconst_0
	goto end_3
true_2:
	iconst_1
end_3:
	ifeq else_0
	ldc 0
	ireturn
	goto endif_1
else_0:
endif_1:
	ldc 2
	ldc 2
	istore 1
fl_start_4:
	iload 0
	ldc 1
	isub
	iload 1
	if_icmplt fl_end_5
	iload 0
	iload 1
	irem
	i2f
	ldc 0
	i2f
	fcmpl
	ifeq true_8
	iconst_0
	goto end_9
true_8:
	iconst_1
end_9:
	ifeq else_6
	ldc 0
	ireturn
	goto endif_7
else_6:
endif_7:
	iload 1
	ldc 1
	iadd
	istore 1
	goto fl_start_4
fl_end_5:
	ldc 1
	ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 1
	ldc 1
	istore 0
fl_start_10:
	ldc 100
	iload 0
	if_icmplt fl_end_11
	iload 0
	invokestatic SumProgram/IsPrime(I)I
	ifeq else_12
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/println(I)V
	goto endif_13
else_12:
endif_13:
	iload 0
	ldc 1
	iadd
	istore 0
	goto fl_start_10
fl_end_11:
	return
.end method
