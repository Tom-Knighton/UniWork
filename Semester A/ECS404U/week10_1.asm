	# All program code is placed after the
	# .text assembler directive

.text
    la  $s0, MY_INT_ARRAY_LEN
    lw  $s0, 0($s0)             # s0: MY_INT_ARRAY_LEN
    addi  $t0, $zero, 0         # t0: to hold the "index" of the next array entry
    la  $t1, MY_INT_ARRAY       # t1: to hold the "address" of the array entries
                                #     initialised to the address of the first
                                #     byte of the array

    NEXT_ARRAY_PRINT:
    beq $t0, $s0, DONE_PRINTING # jump to DONE_PRINTING when reached array's end
    addi $v0, $zero, 1          # set v0 to invoke "print integer" syscall
    lw $t2, 0($t1)              # $t0 <- the value of the current index of the array
    add $t3, $t2, $t2           # t3 <- $t2+$t2
    add $t4, $t3, $t2           # $t4 <- $t3 + $t2, so $t2 * 3
    addu $a0, $zero, $t4       # a0 <- the integer to be printed
    syscall                     # invoking the syscall to print the integer

    addi  $v0, $zero, 4         # set v0 to invoke "print string" syscall
    la $a0, MSG_SPACER          # a0 <- the address of the string's starting byte
    syscall                     # invoking the syscall to print the string

    addi $t1, $t1, 4           # incrementing t1 by 4 (why?)
    addi $t0, $t0, 1           # incrementing t0 (the array index) by 1

    j NEXT_ARRAY_PRINT          # jump to NEXT_ARRAY_PRINT (our for loop)

    DONE_PRINTING:
    addi $v0, $zero, 10         # set v0 to "10" to select exit syscall
    syscall                     # invoking the syscall to actually exit!s

    # All memory structures are placed after the
    # .data assembler directive
.data
    MSG_SPACER:  .asciiz ", "   # the comma (and space) string
    MY_INT_ARRAY:               # our integer array
        .word    1
        .word    2
        .word    3
        .word    4
        .word    5
        .word    6
        .word    7
        .word    8
    MY_INT_ARRAY_LEN:   .word   8 # the length of the array
