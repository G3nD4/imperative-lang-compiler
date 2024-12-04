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
	ldc 10
	istore 0
	ldc 3.14
	fstore 1
	ldc 1
	istore 2
	iload 0
	ldc 5
	iadd
	istore 0
	fload 1
	ldc 2.5
	fmul
	fstore 1
	iload 0
	i2f
	ldc 10
	i2f
	fcmpl
	ifgt true_3
	iconst_0
	goto end_4
true_3:
	iconst_1
end_4:
	ifeq elseif_0
	ldc 0
	istore 2
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/println(I)V
	goto endif_2
elseif_0:
	iload 0
	i2f
	ldc 10
	i2f
	fcmpl
	ifeq true_5
	iconst_0
	goto end_6
true_5:
	iconst_1
end_6:
	ifeq else_1
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc 0
	invokevirtual java/io/PrintStream/println(I)V
	goto endif_2
else_1:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc 1
	ineg
	invokevirtual java/io/PrintStream/println(I)V
endif_2:
	ldc 0.1
	fstore 3
	ldc 1.2
	fstore 4
wl_start_7:
	fload 3
	fload 4
	fcmpl
	iflt true_9
	iconst_0
	goto end_10
true_9:
	iconst_1
end_10:
	ifeq wl_end_8
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 3
	invokevirtual java/io/PrintStream/println(F)V
	fload 3
	ldc 0.1
	fadd
	fstore 3
	goto wl_start_7
wl_end_8:
	iload 0
	i2f
	ldc 5
	i2f
	fcmpl
	ifgt true_11
	iconst_0
	goto end_12
true_11:
	iconst_1
end_12:
	fload 1
	ldc 10
	i2f
	fcmpl
	iflt true_13
	iconst_0
	goto end_14
true_13:
	iconst_1
end_14:
	iand
	iload 2
	ior
	istore 5
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 5
	invokevirtual java/io/PrintStream/println(I)V
	return
.end method
