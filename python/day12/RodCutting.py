cnt = 0

def recMaxProfit(len, price):
	global cnt
	cnt = cnt + 1
	if(len == 0):
		return 0
	max = 0
	for i in range(1, len+1):
		profitRight = recMaxProfit(len-i, price)
		if(price[i] + profitRight > max):
			max = price[i] + profitRight
	return max


def memMaxProfit(len, price, profit):
	global cnt
	cnt = cnt + 1
	if(len == 0):
		profit[0] = 0
		return profit[0]
	if(profit[len] != 0):
		return profit[len]
	max = 0
	for i in range(1, len+1):
		profitRight = memMaxProfit(len-i, price, profit)
		if(price[i] + profitRight > max):
			max = price[i] + profitRight
	profit[len] = max
	return profit[len]


def dynMaxProfit(length, price):
	global cnt
	cnt = cnt + 1
	profit = [0] * len(price)
	for l in range(1,length+1):
		profit[l] = 0
		for i in range(1, l+1):
			if(price[i] + profit[l-i] > profit[l]):
				profit[l] = price[i] + profit[l-i]
	return profit[length]


def dynMaxProfitSoln(length, price):
	global cnt
	cnt = cnt + 1
	profit = [0] * len(price)
	cut = [0] * len(price)
	profit[0] = 0
	for l in range(1,length+1):
		profit[l] = 0
		for i in range(1,l+1):
			if(profit[l] < price[i] + profit[l-i]):
				profit[l] = price[i] + profit[l-i]
				cut[l] = i
	
	print("Cuts = ", end='')
	remain = length
	while(remain > 0):
		print(cut[remain], end=', ')
		remain = remain - cut[remain]
	print()
	
	return profit[length]


if __name__=="__main__":
	rodLen = 8
	price = [0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30]

	cnt = 0
	res = recMaxProfit(rodLen, price)
	print(f"profit: {res} with number of calls: {cnt}")

	profit = [0] * len(price)
	cnt = 0
	res = memMaxProfit(rodLen, price, profit)
	print(f"profit: {res} with number of calls: {cnt}")

	cnt = 0
	res = dynMaxProfit(rodLen, price)
	print(f"profit: {res} with number of calls: {cnt}")
	
	cnt = 0
	res = dynMaxProfitSoln(rodLen, price)
	print(f"profit: {res} with number of calls: {cnt}")

