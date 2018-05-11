#
# makefile for machine problem 1.  The project has two programs: mp1client and
# mp1server.  
#
# The lines that include a ":" are called dependency lines, and the lines
# immediately following a dependency line are command lines.  Command lines
# _must_ start with a tab character, and dependency lines must not start with
# a tab character.
#
# For Solaris use the following compiler flags
#
# comp_flags = -g -Wall -lnsl -lsocket -lresolv
#
# For Linux use these flags
#
comp_flags = -g -Wall 

mp1: mp1client mp1server

mp1client: mp1client.c
	gcc $(comp_flags) -o mp1client mp1client.c

mp1server: mp1server.c
	gcc $(comp_flags) -o mp1server mp1server.c

clean:
	rm -f mp1client mp1server core

