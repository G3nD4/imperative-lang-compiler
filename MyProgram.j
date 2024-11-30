.class public SumProgram
.super java/lang/Object
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method
.method public static sumOfNumbers(I)I
.limit stack 100
.limit locals 100
ldc 0
istore_1
ldc 1
ldc 1
istore_2
for_start_0:
ldc 10
iload_2
if_icmpgt for_end_1
iload_1
iload_2
iadd
istore_1
iload_2
ldc 1
isub
istore_2
goto for_start_0
for_end_1:
ldc 1
istore_3
ldc 1
istore_4
while_start_2:
iload_3
iload_4
iand
ifeq while_end_3
ldc 1
istore_3
goto while_start_2
while_end_3:
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
ldc 0
istore_0
iload_0
invokestatic SumProgram/sumOfNumbers(I)I
return
.end method