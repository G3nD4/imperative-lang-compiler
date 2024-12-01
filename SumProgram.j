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
	i2f
	ldc 2
	i2f
	fdiv
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
.method public static FindLargestPrimeFactor(I)I
.limit stack 100
.limit locals 100
	ldc 2
	istore 1
	ldc 1
	istore 2
wl_start_10:
	iload 0
	i2f
	ldc 1
	i2f
	fcmpl
	ifgt true_12
	iconst_0
	goto end_13
true_12:
	iconst_1
end_13:
	ifeq wl_end_11
	iload 0
	iload 1
	irem
	i2f
	ldc 0
	i2f
	fcmpl
	ifeq true_16
	iconst_0
	goto end_17
true_16:
	iconst_1
end_17:
	ifeq else_14
	iload 1
	invokestatic SumProgram/IsPrime(I)I
	ifeq else_18
	iload 1
	istore 2
	goto endif_19
else_18:
endif_19:
	iload 0
	i2f
	iload 1
	i2f
	fdiv
	invokestatic java/lang/Math/round(F)I
	istore 0
	goto endif_15
else_14:
	iload 1
	ldc 1
	iadd
	istore 1
endif_15:
	goto wl_start_10
wl_end_11:
	iload 2
	ireturn
.end method
.method public static ValidatePrimeFactorization(II)I
.limit stack 100
.limit locals 100
	iload 0
	iload 1
	irem
	i2f
	ldc 0
	i2f
	fcmpl
	ifeq true_22
	iconst_0
	goto end_23
true_22:
	iconst_1
end_23:
	iload 1
	invokestatic SumProgram/IsPrime(I)I
	iand
	ifeq else_20
	ldc 1
	ireturn
	goto endif_21
else_20:
endif_21:
	ldc 0
	ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 84
	istore 0
	ldc 1
	istore 1
	iload 0
	invokestatic SumProgram/FindLargestPrimeFactor(I)I
	istore 1
	iload 1
	iload 0
	invokestatic SumProgram/ValidatePrimeFactorization(II)I
	istore 2
	iload 2
	ifeq else_24
	iload 1
	i2f
	ldc 2.718
	fmul
	fstore 3
	fload 3
	fstore 4
	iload 4
	ldc 1
	isub
	istore 1
	goto endif_25
else_24:
	ldc 1
	ineg
	istore 0
	ldc 1
	ineg
	istore 1
	ldc 0
	istore 2
endif_25:
	ldc 1
	ldc 1
	istore 5
fl_start_26:
	iload 1
	iload 5
	if_icmplt fl_end_27
	iload 5
	ldc 3
	irem
	i2f
	ldc 0
	i2f
	fcmpl
	ifeq true_30
	iconst_0
	goto end_31
true_30:
	iconst_1
end_31:
	iload 2
	iand
	ifeq else_28
	iload 2
	istore 2
	goto endif_29
else_28:
endif_29:
	iload 5
	ldc 1
	iadd
	istore 5
	goto fl_start_26
fl_end_27:
	return
.end method
