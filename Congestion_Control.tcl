#===================================
# Simulation Setup
#===================================
set val(stop) 5.0  ;# End time of simulation

# Initialize the simulator
set ns [new Simulator]

# Trace files for NS and NAM
set tracefile [open sliding.tr w]
set namfile [open sliding.nam w]
$ns trace-all $tracefile
$ns namtrace-all $namfile

#===================================
# Nodes Definition
#===================================
# Create two nodes
set n0 [$ns node]
set n1 [$ns node]
$ns at 0.0 "$n0 label Sender"
$ns at 0.0 "$n1 label Receiver"

#===================================
# Link Definition
#===================================
# Create a duplex link with bandwidth, delay, and DropTail queue
$ns duplex-link $n0 $n1 0.2Mb 200ms DropTail
$ns queue-limit $n0 $n1 10
$ns duplex-link-op $n0 $n1 orient right

#===================================
# Agent and Application Setup
#===================================
# Configure TCP and attach to nodes
set tcp [new Agent/TCP]
$tcp set windowInit_ 4
$tcp set maxcwnd_ 4
$tcp set packetSize_ 500
$ns attach-agent $n0 $tcp

set sink [new Agent/TCPSink]
$ns attach-agent $n1 $sink

$ns connect $tcp $sink

# FTP Application over TCP
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ns at 0.1 "$ftp start"
$ns at 3.5 "$ftp stop"

#===================================
# Termination Setup
#===================================
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam sliding.nam &
    exit 0
}

# Schedule simulation end
$ns at $val(stop) "finish"

# Run the simulation
$ns run
