# Mark Weiman
# https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-01/src/plot.r
# COSC 311
# PROJECT 01
# WINTER 2017

url1 <- "file:///home/markzz/Documents/emu-wn17/one-server.csv"
table <- read.table(file = url1, header = TRUE, sep = ",")

url2 <- "file:///home/markzz/Documents/emu-wn17/two-servers.csv"
table2 <- read.table(file = url2, header = TRUE, sep = ",")

png(filename = "/home/markzz/Documents/emu-wn17/benefit.png")
plot(table$Benefit, type = "o", col = "blue", xlim = c(1,50))
lines(table2$Benefit, type = "o", col = "red")
title("Benefit")
dev.off()

png(filename = "/home/markzz/Documents/emu-wn17/waittime.png")
plot(table$WaitTime, type = "o", col = "blue", xlim = c(1,50))
lines(table2$WaitTime, type = "o", col = "red")
title("Wait time")
dev.off()

png(filename = "/home/markzz/Documents/emu-wn17/served")
plot(table$Served, type = "o", col = "blue", xlim = c(1,50))
lines(table2$Served, type = "o", col = "red")
title("Number Served")
dev.off()
