#!/bin/bash
# ECE 4380/6380
# Spring 2018
# Clemson University
# Prof. Harlan B. Russell
#
# This script disables the static routes generated and installed by GENI and
# installs quagga.  http://www.nongnu.org/quagga/
#
# Quagga is an open source implemenation of many routing protocols including
# OSPF.  Configuring OSPF is tedious, so this script automates setting up
# the configuration files assuming that all interfaces except eth0 should
# be used by OSPF.
#
# The script tests for the ospfd configuration file, and if it exits
# does not do any configuration steps.  However, the script can be 
# re-run by first deleting the file at
# 
#    /etc/quagga/ospfd.conf
#
# This script is based on one from:
# Ben Newton
# University of North Carolina at Chapel Hill
# October 2, 2014
##

DAEMONS="/etc/quagga/daemons"
DAEMONS_TEMP="/tmp/daemons"
ZEBRA_SAMPLE="/usr/share/doc/quagga/examples/zebra.conf.sample"
ZEBRA="/etc/quagga/zebra.conf"
ZEBRA_TEMP="/tmp/zebra"
OSPFD_SAMPLE="/usr/share/doc/quagga/examples/ospfd.conf.sample"
OSPFD="/etc/quagga/ospfd.conf"
OSPFD_TEMP="/tmp/ospfd"
VTYSH_SAMPLE="/usr/share/doc/quagga/examples/vtysh.conf.sample"
VTYSH="/etc/quagga/vtysh.conf"
VTYSH_TEMP="/tmp/vtysh"

# disable Static routes installed by GENI.
sudo /var/emulab/boot/rc.route disable-routes


# look for this file, and if it is there assume we are rebooting instead of 
# booting the first time, therefore everything is alreay installed and 
# configured, so skip.
if [[ ! -f $OSPFD ]]; then
    #first update
    sudo apt-get update

    #install quagga and traceroute
    sudo apt-get -y install quagga traceroute

    #enable ospf daemon and zebra
    sudo mv $DAEMONS $DAEMONS.orig
    sudo chmod +r $DAEMONS.orig
    sudo sed -e 's/ospfd=no/ospfd=yes/g' -e 's/zebra=no/zebra=yes/g' < $DAEMONS.orig > $DAEMONS_TEMP
    sudo mv $DAEMONS_TEMP $DAEMONS
    
    #determine first part of hostname
    HOST=`hostname | awk -F'.' '{print $1}'`

    #make list of all eth interfaces expect 0
    ETHNUMS=`ip addr show | grep -Eo 'eth[1-9][0-9]*$'`
    
    #copy sample zebra config file and modify
    sudo sed -e 's/hostname Router/hostname '$HOST'/g' < $ZEBRA_SAMPLE > $ZEBRA_TEMP
    sudo echo "interface lo" >> $ZEBRA_TEMP
    sudo echo " description loopback" >> $ZEBRA_TEMP
    sudo echo " ip address 127.0.0.1/8" >> $ZEBRA_TEMP
    sudo echo " ip forwarding" >> $ZEBRA_TEMP
    # for each interface add ip address with mask
    for ETH in ${ETHNUMS[@]} ; do
        sudo echo "!" >> $ZEBRA_TEMP
        sudo echo "interface $ETH" >> $ZEBRA_TEMP
        sudo echo " description $ETH" >> $ZEBRA_TEMP
        # this extracts the full IP address with mask 
        ETHIP=`ip addr | grep $ETH$ | awk -F " " '{print $2}'`
        sudo echo " ip address $ETHIP" >> $ZEBRA_TEMP
        sudo echo " ip forwarding" >> $ZEBRA_TEMP
    done
    sudo echo "log file /var/log/quagga/zebra.log" >> $ZEBRA_TEMP
    sudo mv $ZEBRA_TEMP $ZEBRA

    #copy sample ospfd config file and modify
    sudo cp $OSPFD_SAMPLE $OSPFD_TEMP
    sudo chmod ugo+rw $OSPFD_TEMP
    # must list each interface 
    for ETH in ${ETHNUMS[@]} ; do
        sudo echo "interface $ETH" >> $OSPFD_TEMP
        # could add interface options here like cost
    done
    sudo echo "router ospf" >> $OSPFD_TEMP
    # next list the networks this router should advertise
    # this replaces the host bits with a zero to create the subnet number 
    # with mask.  This is a hack that only works if the mask is /24, and 
    # the host address is a single digit!  Beware of reusing this...
    for ETH in ${ETHNUMS[@]} ; do
        NET=`ip addr | grep $ETH$ | awk -F " " '{print $2}' | sed -e 's/.\//0\//g'`
        sudo echo "network $NET area 0" >> $OSPFD_TEMP
    done
    sudo echo "log file /var/log/quagga/ospfd.log" >> $OSPFD_TEMP
    sudo mv $OSPFD_TEMP $OSPFD

    #copy sample vtysh config file and disable integrated service
    sudo sed -e 's/^\(service integrated-vtysh-config\)/! \0/' < $VTYSH_SAMPLE > $VTYSH_TEMP
    sudo mv $VTYSH_TEMP $VTYSH

    # set permissions for config files
    sudo chown quagga.quaggavty /etc/quagga/*.conf $DAEMONS
    
    #append line to environment to ensure VTYSH works right if empty
    if [[ -z `grep VTYSH_PAGER /etc/environment` ]]; then
        sudo cp /etc/environment /tmp/environment
        sudo chmod ugo+rw /tmp/environment
        sudo echo "VTYSH_PAGER=more" >> /tmp/environment
        sudo mv /tmp/environment /etc/environment
    fi
    if [[ -z `grep VTYSH_PAGER /etc/bash.bashrc` ]]; then
        sudo cp /etc/bash.bashrc /tmp/bash.bashrc
        sudo chmod ugo+rw /tmp/bash.bashrc
        sudo echo "export VTYSH_PAGER=more" >> /tmp/bash.bashrc
        sudo mv /tmp/bash.bashrc /etc/bash.bashrc
    fi
    
    #restart quagga
    sudo /etc/init.d/quagga restart
fi

