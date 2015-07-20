package com.exadel.training.common;

/**
 * Created by HP on 12.07.2015.
 */
public enum RoleType {
    Admin,
    Employee,
    ExCoach,
    ExEmployee;

    public static RoleType parseIntToRoleType(int type) throws NoSuchFieldException {
        switch (type) {
            case 1: {
                return RoleType.Admin;
            }
            case 2: {
                return  RoleType.Employee;
            }
            case 3: {
                return  RoleType.ExCoach;
            }
            case  4: {
                return  RoleType.ExEmployee;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static long parseRoleTypeToLong(RoleType type) throws NoSuchFieldException    {
        switch (type) {
                case Admin: {
                    return 1l;
                }
                case Employee: {
                    return 2l;
                }
                case ExCoach: {
                    return 3l;
                }
                case ExEmployee: {
                    return 4l;
                }
                default: {
                    throw new NoSuchFieldException("don't find such type.");
                }
            }
        }
}
