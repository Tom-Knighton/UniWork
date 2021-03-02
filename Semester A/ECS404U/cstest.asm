.text
    li $v0, 4
    la $a0, msg1
    syscall
    li $v0, 5
    syscall
    add $t1, $zero, $v0

    li $v0, 4,
    la $a0, msg2
    syscall

    li $v0, 5
    syscall
    add $t2, $zero, $v0
    add $t0, $zero, $zero

    LOOP:
    slt $t3, $t1, $t2
    bne $t3, $zero, DONE
    sub $t1, $t1, $t2
    addi $t0, $t0, 1
    j LOOP

    DONE:
    li $v0, 4
    la $a0, msg3
    syscall

    li $v0, 1
    add $a0, $t0, $zero
    syscall

    li $v0, 4
    la $a0, msg4
    syscall

    li $v0, 1
    add $a0, $t1, $zero
    syscall

    li $v0, 10
    syscall
    
.data
    msg1: .asciiz "\nEnter first integer:"
    msg2: .asciiz "Enter second integer:"
    msg3: .asciiz "Result: "
    msg4: .asciiz "\nRemainder: "
