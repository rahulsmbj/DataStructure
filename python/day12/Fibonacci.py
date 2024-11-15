
cnt = 0

def recFib(n):
	global cnt
	cnt = cnt + 1
	if(n==1 or n==2):
		return 1
	return recFib(n-1) + recFib(n-2)


def memFib(n, terms):
	global cnt
	cnt = cnt + 1
	if(n==1 or n==2):
		terms[n] =  1
		return terms[n]
	if(terms[n] != 0):
		return terms[n]
	terms[n] = memFib(n-1, terms) + memFib(n-2, terms)
	return terms[n]

	
def dynFib(n):
	global cnt
	cnt = cnt + 1
	terms = [0] * (n+1)
	terms[1] = terms[2] = 1
	for i in range(3, n+1):
		terms[i] = terms[i-1] + terms[i-2]
	return terms[n]
	

if __name__=="__main__":
	n=10

	cnt = 0
	res = recFib(n)
	print(f"Result = {res} with fn calls {cnt}")

	terms = [0] * (n+1)
	cnt = 0
	res = memFib(n, terms)
	print(f"Result = {res} with fn calls {cnt}")

	cnt = 0
	res = dynFib(n)
	print(f"Result = {res} with fn calls {cnt}")

