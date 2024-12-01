.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static add(F)V
.limit stack 100
.limit locals 100
	ldc 4
	istore_1
	ldc 100
	istore_2
	iload_1
	ldc 2
	irem
	i2f
	ldc 0
	i2f
	fcmpl
	ifeq true_6
	iconst_0
	goto end_7
true_6:
	iconst_1
end_7:
	ifeq elseif_0
	ldc 1
	istore_2
	goto endif_5
elseif_0:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_8
	iconst_0
	goto end_9
true_8:
	iconst_1
end_9:
	ifeq elseif_1
	ldc 2
	istore_2
	goto endif_5
elseif_1:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_10
	iconst_0
	goto end_11
true_10:
	iconst_1
end_11:
	ifeq elseif_2
	ldc 2
	istore_2
	goto endif_5
elseif_2:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_12
	iconst_0
	goto end_13
true_12:
	iconst_1
end_13:
	ifeq elseif_3
	ldc 2
	istore_2
	goto endif_5
elseif_3:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_14
	iconst_0
	goto end_15
true_14:
	iconst_1
end_15:
	ifeq else_4
	ldc 2
	istore_2
	goto endif_5
else_4:
	iload_1
	ldc 2
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
	ifeq elseif_16
	ldc 1
	istore_2
	goto endif_21
elseif_16:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_24
	iconst_0
	goto end_25
true_24:
	iconst_1
end_25:
	ifeq elseif_17
	ldc 2
	istore_2
	goto endif_21
elseif_17:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_26
	iconst_0
	goto end_27
true_26:
	iconst_1
end_27:
	ifeq elseif_18
	ldc 2
	istore_2
	goto endif_21
elseif_18:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_28
	iconst_0
	goto end_29
true_28:
	iconst_1
end_29:
	ifeq elseif_19
	ldc 2
	istore_2
	goto endif_21
elseif_19:
	iload_1
	ldc 2
	irem
	i2f
	ldc 3
	i2f
	fcmpl
	ifeq true_30
	iconst_0
	goto end_31
true_30:
	iconst_1
end_31:
	ifeq else_20
	ldc 2
	istore_2
	goto endif_21
else_20:
	ldc 3
	istore_2
endif_21:
endif_5:
	return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
	ldc 1.0
	invokestatic SumProgram/add(F)V
	return
.end method
