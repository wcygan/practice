# Hardware and Operating Systems

## Translation Lookaside Buffer (TLB)

The TLB acts as a cache for page tables that map virtual memory addresses to physical addresses, which greatly speeds up
a very frequent operation -- access to the physical address underlying a virtual address.

Without the TLB, all virtual address lookups would take 16 cycles, even if the page table was held in L1 cache.

## Branch Prediction and Speculative Execution

Branch prediction is used to prevent the processor from having to wait to evaluate a value needed for a conditional
branch. In a CPU, instructions are pipelined; having to evaluate this value lazily could result in the CPU stalling for
up to 20 cycles.

To avoid this, the processor can dedicate transistors to building up a heuristic to decide which branch is more likely
to be taken. Using this guess, the CPU fills the pipeline based on a gamble. If it works, then the CPU carries on as
thought nothing had happened. If it's wrong, then the partially executed instructions are dumped, and the CPU has to pay
the penalty of emptying the pipeline.