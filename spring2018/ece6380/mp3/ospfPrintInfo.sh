#!/bin/bash
# ECE 6380
# Spring 2018
# Clemson University
# Christopher Brant
#
# This script dprints out interface names, IP addresses, subnet numbers, and mask lengths
# for any router in the given network
#
# This script is based on one from:
# Harlan Russell
# Clemson University
# April 24th, 2018
##

#make list of all eth interfaces expect 0
ETHNUMS=`ip addr show | grep -Eo 'eth[1-9][0-9]*$'`
    
# for each interface add ip address with mask
for ETH in ${ETHNUMS[@]} ; do
    # this extracts the full IP address with mask 
    ETHIP=`ip addr | grep $ETH$ | awk -F " " '{print $2}'`
    INDIP="${ETHIP%%/*}"
    SUBMASK="${ETHIP: (-2)}"
    SUBNET="${ETHIP%.*/$SUBMASK}"
    SUBNETIP="$SUBNET.0/$SUBMASK"
    sudo echo "$ETH has IP address $INDIP and connects to subnet $SUBNETIP"
done