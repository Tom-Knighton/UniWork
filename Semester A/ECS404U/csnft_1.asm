.text
addi $s0, $zero, 6
addi $t0, $zero, 0

addi $t1, $zero, 2
addi $t2, $zero, 0

LOOP:
addi $t0, $t0, 1
addi $v0, $zero, 1
add $a0, $zero, $t1
syscall

beq $t0, $s0, DONE
add $t3, $t2, $t2
addi $t3, $t3, 1
add $t4, $t1, $t3
add $t1, $zero, $t2
add $t2, $zero, $t4

addi $v0, $zero, 4
la $a0, COMMA_FOR_SEPARATION
syscall

j LOOP

DONE:
addi $v0, $zero, 10
syscall

.data
COMMA_FOR_SEPARATION: .asciiz ", "
