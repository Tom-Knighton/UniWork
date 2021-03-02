addiu $s0, $zero, 1
addiu $s1, $zero, -1
addiu $s2, $zero, 2
addiu $s3, $zero, -2

addu $t0, $s0, $s2
addu $t1, $s1, $s3
addu $t2, $s1, $s2
addu $t3, $s0, $s3
addu $t4, $s2, $s2
addu $t5, $s3, $s3

addiu $v0, $zero, 10
syscall