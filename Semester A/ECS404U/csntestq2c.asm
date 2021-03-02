.text
    addi $t0, $zero, 81
    andi $t1, $t0, 0x0007

    addi $v0, $zero, 1         # set v0 to "1" to select
                               # "print integer" syscall
    add  $a0, $zero, $t1      # a0 <- s0 (the total sum) to be printed
	syscall                    # invoking the syscall to print the integer

    addi $v0, $zero, 10
    syscall