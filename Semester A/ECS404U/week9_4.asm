	# All program code is placed after the
	# .text assembler directive
  
.text

  addi $s0, $zero, 5    # sets $s0 to our number to count to (5)
  addi $t1, $zero, 1    # sets $t0 to 1 (counter)
  addi $t2, $zero, 1    # sets $t1 to 1 (sum)

  ADDITION:
  beq $t1, $s0, EXIT    # if our counter is equal to our number, exit loop
  addi $t1, $t1, 1      # adds 1 to our counter
  add $t2, $t2, $t1     # adds the counter to the running total sum

  j ADDITION            # jumps to the ADDITION loop

  EXIT:
  addi $v0, $zero, 4    # sets $v0 to 4 to print string
  la $a0, TOTAL_MSG     # loads address of first byte of message into $a0
  syscall               # prints string

  addi $v0, $zero, 1    # sets $v0 to 1 to print integer
  add $a0, $zero, $t2   # sets $a0 to the value of $t2 (total sum)
  syscall               # prints integer

  addi $v0, $zero 10    # sets $v0 to 10 to exit on syscall
  syscall

.data
  TOTAL_MSG: .asciiz "The total sum is:"




