	# All program code is placed after the
	# .text assembler directive
  
.text

  addi $t0, $zero, 1          # sets the value of $t0 to 1
  beq $s0, $zero, PRINT_ZERO  # if $s0 is equal to zero, branch to PRINT_ZERO
  beq $s0, $t0, PRINT_ONE     # if $s0 is equal to $t0 (1), branch to PRINT_ONE
  j PRINT_ELSE                # (else) jump to PRINT_ELSE

  PRINT_ZERO:                 
  addi $v0, $zero, 4          # sets value of $v0 to 4 to print string
  la $a0, ZERO_MSG            # loads the address of first byte of string into $a0
  syscall                     # prints string
  j EXIT                      # jumps to EXIT

  PRINT_ONE:    
  addi $v0, $zero, 4          # sets value of $v0 to 4 to print string
  la $a0, ONE_MSG             # loads the address of first byte of string into $a0
  syscall                     # prints string
  j EXIT                      # jumps to EXIT

  PRINT_ELSE:
  addi $v0, $zero, 4          # sets value of $v0 to 4 to print string
  la $a0, ELSE_MSG            # loads the address of first byte of string into $a0
  syscall                     # prints string
  j EXIT                      # jumps to EXIT


  EXIT:
  addi $v0, $zero, 10        # sets value of $v0 to 10 to exit on syscall
  syscall

.data
  ZERO_MSG: .asciiz "it was zero!"
  ONE_MSG: .asciiz "it was one!"
  ELSE_MSG: .asciiz "it was neither!"

