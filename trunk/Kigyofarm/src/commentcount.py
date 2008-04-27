"""
Ez a kis script megszamoja, hogy hany % comment van a kodban.
Ha van valakinek kedve, irja ujra javaban :P
"""

import os

flist = []
for root, dirs, files in os.walk("."):
	for file in files:
		if file[-5:] == ".java":
			flist.append(os.path.join(root, file))
flist.sort()

nlines = 0
clines = 0
for i in flist:
	sock = open(i)
	lines = sock.readlines()
	sock.close()
	incomment = False
	for j in lines:
		nlines += 1
		if j[-4:] == "/**\n":
			incomment = True
		if incomment:
			clines += 1
		if j[-3:] == "*/\n":
			incomment = False
print "%s / %s = %s" % (clines, nlines, float(clines)/nlines)
