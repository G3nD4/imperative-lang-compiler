.class public MyProgram
.super java/lang/Object

; Main method
.method public static main([Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    .limit stack 100                ; Limit the stack usage
    .limit locals 100               ; Limit the local variables

ldc 5
i2f
ldc 8
i2f
swap
fcmpl
iflt less_than_0
iconst_0
less_than_0:
iconst_1
end_1:
.end method
