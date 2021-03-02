	# All program code is placed after the
	# .text assembler directive
  
.text
  addi $v0, $zero, 4  # requests 4 to syscall to print string
  la $a0, ENTER_MSG   # loads address of starting byte of string into $a0
  syscall             # prints string

  addi $v0, $zero, 5  # requests 5 to syscall to read integer
  syscall             # reads integer
  add $t0, $zero, $v0 # adds value entered by user into $t0

  addi $v0, $zero, 4  # requests 4 to syscall to print string
  la $a0, ENTERED_MSG # loads address of starting byte of string into #a0
  syscall             # prints string

  addi $v0, $zero, 1  # requests 1 to syscall to print integer
  add $a0, $zero, $t0 # adds value of $t0 into $a0
  syscall             # prints integer

  addi $v0, $zero, 10 # will exit program with syscall
  syscall

.data
   ENTER_MSG: .asciiz "Please enter an integer number: "
   ENTERED_MSG: .asciiz "The number you entered was: "
