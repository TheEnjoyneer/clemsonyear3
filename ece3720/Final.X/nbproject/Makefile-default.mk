#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Include project Makefile
ifeq "${IGNORE_LOCAL}" "TRUE"
# do not include local makefile. User is passing all local related variables already
else
include Makefile
# Include makefile containing local settings
ifeq "$(wildcard nbproject/Makefile-local-default.mk)" "nbproject/Makefile-local-default.mk"
include nbproject/Makefile-local-default.mk
endif
endif

# Environment
MKDIR=gnumkdir -p
RM=rm -f 
MV=mv 
CP=cp 

# Macros
CND_CONF=default
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
IMAGE_TYPE=debug
OUTPUT_SUFFIX=elf
DEBUGGABLE_SUFFIX=elf
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}
else
IMAGE_TYPE=production
OUTPUT_SUFFIX=hex
DEBUGGABLE_SUFFIX=elf
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}
endif

# Object Directory
OBJECTDIR=build/${CND_CONF}/${IMAGE_TYPE}

# Distribution Directory
DISTDIR=dist/${CND_CONF}/${IMAGE_TYPE}

# Source Files Quoted if spaced
SOURCEFILES_QUOTED_IF_SPACED=final.c

# Object Files Quoted if spaced
OBJECTFILES_QUOTED_IF_SPACED=${OBJECTDIR}/final.o
POSSIBLE_DEPFILES=${OBJECTDIR}/final.o.d

# Object Files
OBJECTFILES=${OBJECTDIR}/final.o

# Source Files
SOURCEFILES=final.c


CFLAGS=
ASFLAGS=
LDLIBSOPTIONS=

############# Tool locations ##########################################
# If you copy a project from one host to another, the path where the  #
# compiler is installed may be different.                             #
# If you open this project with MPLAB X in the new host, this         #
# makefile will be regenerated and the paths will be corrected.       #
#######################################################################
# fixDeps replaces a bunch of sed/cat/printf statements that slow down the build
FIXDEPS=fixDeps

.build-conf:  ${BUILD_SUBPROJECTS}
ifneq ($(INFORMATION_MESSAGE), )
	@echo $(INFORMATION_MESSAGE)
endif
	${MAKE}  -f nbproject/Makefile-default.mk dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}

MP_PROCESSOR_OPTION=32MX150F128D
MP_LINKER_FILE_OPTION=
# ------------------------------------------------------------------------------------
# Rules for buildStep: doLTO
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}: ${SOURCEFILES}  nbproject/Makefile-${CND_CONF}.mk    
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	@${RM} $(POSSIBLE_DEPFILES) 
	${MP_CC}  $(MP_EXTRA_CC_PRE) $(MP_EXTRA_AS_PRE) $(MP_EXTRA_LD_PRE)  -g -D__DEBUG -mdebugger -D__MPLAB_DEBUGGER_PK3=1 -fframe-base-loclist  -flto -fwhole-program -mprocessor=$(MP_PROCESSOR_OPTION) -I"../../../../../../Applications/microchip/xc32/v1.40" -I"mchp_peripheral_32MX150D128F" -MMD  -o dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}      ${SOURCEFILES_QUOTED_IF_SPACED}     -legacy-libc    -mreserve=boot@0x1FC00490:0x1FC00BEF  -Wa,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_AS_POST),--defsym=__ICD2RAM=1,--defsym=__MPLAB_DEBUG=1,--defsym=__DEBUG=1,--defsym=__MPLAB_DEBUGGER_PK3=1,--gdwarf-2 -I"../../../../../../Applications/microchip/xc32/v1.40" -I"mchp_peripheral_32MX150D128F" -Wl,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_LD_POST)$(MP_LINKER_FILE_OPTION),--defsym=__MPLAB_DEBUG=1,--defsym=__DEBUG=1,--defsym=__MPLAB_DEBUGGER_PK3=1,-Map="${DISTDIR}/${PROJECTNAME}.${IMAGE_TYPE}.map",--memorysummary,dist/${CND_CONF}/${IMAGE_TYPE}/memoryfile.xml
	
	
else
dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${OUTPUT_SUFFIX}: ${SOURCEFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	@${RM} $(POSSIBLE_DEPFILES) 
	${MP_CC}  $(MP_EXTRA_CC_PRE) $(MP_EXTRA_AS_PRE) $(MP_EXTRA_LD_PRE)  -g -g -flto -fwhole-program -mprocessor=$(MP_PROCESSOR_OPTION) -I"../../../../../../Applications/microchip/xc32/v1.40" -I"mchp_peripheral_32MX150D128F" -MMD  -o dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${DEBUGGABLE_SUFFIX}      ${SOURCEFILES_QUOTED_IF_SPACED}     -legacy-libc  -Wa,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_AS_POST),--gdwarf-2 -I"../../../../../../Applications/microchip/xc32/v1.40" -I"mchp_peripheral_32MX150D128F" -Wl,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_LD_POST)$(MP_LINKER_FILE_OPTION),-Map="${DISTDIR}/${PROJECTNAME}.${IMAGE_TYPE}.map",--memorysummary,dist/${CND_CONF}/${IMAGE_TYPE}/memoryfile.xml
	${MP_CC_DIR}\\xc32-bin2hex dist/${CND_CONF}/${IMAGE_TYPE}/Final.X.${IMAGE_TYPE}.${DEBUGGABLE_SUFFIX} 
	
endif


# Subprojects
.build-subprojects:


# Subprojects
.clean-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r build/default
	${RM} -r dist/default

# Enable dependency checking
.dep.inc: .depcheck-impl

DEPFILES=$(shell mplabwildcard ${POSSIBLE_DEPFILES})
ifneq (${DEPFILES},)
include ${DEPFILES}
endif
