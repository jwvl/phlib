table = selected("Table")
newTable = Copy: "copy"
numRows = Get number of rows
numCols = Get number of columns
for row to numRows
	for col to numCols
		col$ = Get column label: col
		oldValue$ = Get value: row, col$
		if oldValue$ = "-"
			newValue$ = "-1"
		else if oldValue$ ="+"
			newValue$ = "1"
		else if oldValue$ = "0"
			newValue$ = "0"
		endif
		Set string value: row, col$, newValue$
	endfor
endfor