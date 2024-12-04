from scapy.all import *

def packet_handler(packet):
    print(packet.show())

# Replace 'Ethernet 8' with the exact name if necessary
sniff(iface='Ethernet 8', prn=packet_handler)
