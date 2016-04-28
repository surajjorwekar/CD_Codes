class Analyser:
	keywords=[
			"auto",
			"break",
			"case",
			"char",
			"const",
			"continue",
			"default",
			"do",
			"double",
			"else",
			"enum",
			"extern",
			"float",
			"for",
			"goto",
			"if",
			"int",
			"long",
			"register",
			"return",
			"short",
			"signed",
			"sizeof",
			"static",
			"struct",
			"switch",
			"typedef",
			"union",
			"unsigned",
			"void",
			"volatile",
			"while"
			]
	operators=[
			"+",
			"-",
			"*",
			"/",
			"=",
			"%",
			"++",
			"--",
			"==",
			"!=",
			">",
			"<",
			">=",
			"<=",
			"&&",
			"||",
			"!",
			"&",
			"|"
			"^",
			"~"
			]
	delimiters=[
			"(",
			")",
			"{",
			"}",
			",",
			";",
			]
	spaces=[
			" ",
			"",
			"\n",
			"\t"
		   ]
	symbols=[]
	count=0
	lexeme=""
	invalid_symbols=[]
	invalid_operators=[]

	def readLexeme(self):
		self.lexeme=self.lexeme+f.read(1)
		length=len(self.lexeme)

		while self.isDelimiter(self.lexeme[length-1])==False and self.lexeme[length-1] not in self.operators and self.lexeme[length-1]!=" ":
			self.lexeme=self.lexeme+f.read(1)
			length=len(self.lexeme)
			self.count+=1

		self.lexeme=self.lexeme[:-1]
		f.seek(self.count-1)

	def isKeyword(self):

		self.readLexeme()
		if(self.lexeme in self.keywords):
			return True
		return False

	def isDelimiter(self,lexeme):
		if lexeme in self.delimiters:
			return True
		else:
			return False

	def isOperator(self):
		if self.lexeme in self.operators:
			self.lexeme=self.lexeme+f.read(1)
			if self.lexeme in self.operators:
				self.count=self.count+1
				return True
			else:
				self.lexeme=self.lexeme[:-1]
				f.seek(self.count-1)
				return True
		else:
			if(self.lexeme.isalpha()!=True and self.lexeme.isalnum()!=True and self.lexeme not in self.spaces):
				self.invalid_operators.append(self.lexeme)
			return False
		
	def isIdentifier(self):

		if(self.lexeme not in self.symbols):
			if(self.lexeme[0].isalpha()):
				if(self.lexeme.isalnum()):
					self.symbols.append(self.lexeme)
					return True
		return False
	
	def isNumber(self):
		if(self.lexeme.isdigit()):
			self.readLexeme()
			if(self.lexeme.isdigit()):
				return True
			else:
				self.invalid_symbols.append(self.lexeme)
		return False

	def isComment(self):
			self.lexeme=self.lexeme+f.read(1)
			if(self.lexeme=="/*"):
				self.count+=1
				length=len(self.lexeme)	
				while self.lexeme[length-1]!="/" or self.lexeme[length-2]!="*":
					self.lexeme=self.lexeme+f.read(1)	
					length=len(self.lexeme)
					self.count+=1	
				return True
			elif(self.lexeme=="//"):
				f.readline()
				self.lexeme=""
			else:
				self.lexeme=self.lexeme[:-1]
				f.seek(self.count-1)
				return False
					

Lex=Analyser()	
f=open("sample.txt","r+")	
Lex.lexeme=f.read(1)
Lex.count=1
print ("\nLexeme\t\tToken\n")

while True:
	Lex.count+=1
	if(Lex.isComment()):
		Lex.lexeme=f.read(1)

	if(Lex.lexeme.isalpha()):
		if(Lex.isKeyword()):
			print (Lex.lexeme,"\t\tkeyword")
		else:
				if(Lex.isIdentifier()):
					print (Lex.lexeme,"\t\tidentifier")

	elif (Lex.isNumber()):
		print (Lex.lexeme,"\t\tconstant")

	elif(Lex.isDelimiter(Lex.lexeme)):
		print (Lex.lexeme,"\t\tdelimiter")

	elif(Lex.isOperator()):
		print (Lex.lexeme,"\t\toperator")
	Lex.lexeme=f.read(1)
	if Lex.lexeme == "":	#End of file
		break

print ("\nERROR:Invalid identifier"),
for word in Lex.invalid_symbols:
	print ("'"+word+"'"),

print ("\nERROR:Invalid operators"),
for op in Lex.invalid_operators:
	print ("'"+op+"'"),

print ("\n\n-----SYMBOL TABLE-----")
for i in Lex.symbols:
	print (i)
