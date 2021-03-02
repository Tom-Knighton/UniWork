.text
    la  $t0, A_LENGTH
    lw  $t0, 0($t0)             # t0 <- A_LENGTH
    la  $t1, A                  # t1: to hold the "address" of the next
                                # array element, initialised to the
                                # address of the first byte of the array
    addi $s0, $zero, 0          # s0: will hold the amount of values / 8

    NEXT_ARRAY_ELEMENT:

    slt $t3, $zero, $t0           # t3<-(0<t0), t3 will be 0 if t0<=0
    beq $t3, $zero, DONE

    lw $t2, 0($t1)              # t2 <- the current array element
   
    addiu $t1, $t1, 4           # t1<-t1+4, to get the address of
                                # the the next element
    addiu $t0, $t0, -1          # decrementing t0 by 1

    andi $t4, $t2, 0x0007       # if $t2 is divisible by 8
    beq $t4, $zero, INCREMENT   # if so, increment $s0

    j NEXT_ARRAY_ELEMENT        # jump to NEXT_ARRAY_ELEMENT (for loop)

    DONE:
    addi $v0, $zero, 1         # set v0 to "1" to select
                               # "print integer" syscall
    add  $a0, $zero, $s0       # a0 <- s0 (the total sum) to be printed
	syscall                    # invoking the syscall to print the integer

    addi $v0, $zero, 10        # set v0 to "10" to select exit syscall
	syscall                    # invoking the syscall to actually exit!

    INCREMENT:
    addi $s0, $s0, 1            # increments $s0 by 1
    j NEXT_ARRAY_ELEMENT

.data
    A:                          # our integer array
        .word   -1
        .word   4
        .word   -16
        .word   0
        .word   -2
        .word   5
        .word   13
        .word   2
    A_LENGTH:   .word   8         # the length of the array
